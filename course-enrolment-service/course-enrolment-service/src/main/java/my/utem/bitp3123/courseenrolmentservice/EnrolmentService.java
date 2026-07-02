package my.utem.bitp3123.courseenrolmentservice;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EnrolmentService {

    private final List<Enrolment> enrolments = new ArrayList<>();
    private int nextId = 1;

    private final CourseService courseService;
    private final StudentClient studentClient;
    private final EnrolmentEventPublisher eventPublisher;

    public EnrolmentService(CourseService courseService,
                            StudentClient studentClient,
                            EnrolmentEventPublisher eventPublisher) {

        this.courseService = courseService;
        this.studentClient = studentClient;
        this.eventPublisher = eventPublisher;
    }

    public List<Enrolment> findAll() {
        return enrolments;
    }

    public long countByCourseCode(String courseCode) {
        return enrolments.stream()
                .filter(e -> e.getCourseCode()
                        .equalsIgnoreCase(courseCode))
                .count();
    }

    public Enrolment enrol(EnrolmentRequest request) {

        boolean studentExists =
                studentClient.studentExists(request.getStudentId());

        if (!studentExists) {
            throw new IllegalArgumentException("Student not found");
        }

        Course course = courseService
                .findByCode(request.getCourseCode())
                .orElseThrow(() ->
                        new IllegalArgumentException("Course not found")
                );

        long currentEnrolmentCount =
                countByCourseCode(request.getCourseCode());

        if (currentEnrolmentCount >= course.getCapacity()) {
            throw new IllegalStateException("Course is full");
        }

        boolean alreadyEnrolled = enrolments.stream()
                .anyMatch(e ->
                        e.getStudentId()
                                .equalsIgnoreCase(request.getStudentId())
                        &&
                        e.getCourseCode()
                                .equalsIgnoreCase(request.getCourseCode())
                );

        if (alreadyEnrolled) {
            throw new IllegalStateException(
                    "Student already enrolled in this course"
            );
        }

        Enrolment enrolment = new Enrolment(
                nextId++,
                request.getStudentId(),
                request.getCourseCode(),
                LocalDateTime.now().toString()
        );

        enrolments.add(enrolment);
        eventPublisher.publishEnrolmentSuccess(enrolment);

        return enrolment;
    }
}