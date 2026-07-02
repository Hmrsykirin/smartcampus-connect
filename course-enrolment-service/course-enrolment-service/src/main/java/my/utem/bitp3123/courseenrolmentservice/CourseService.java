package my.utem.bitp3123.courseenrolmentservice;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CourseService {

    private final List<Course> courses = new ArrayList<>(List.of(
        new Course("BITP3123", "Distributed Application Development", 2),
        new Course("BITP3453", "Mobile Application Development", 30),
        new Course("BITS2513", "Internet Technology", 25)
    ));

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findByCode(String courseCode) {
        return courses.stream()
                .filter(c -> c.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst();
    }

    public Course create(Course course) {
        courses.add(course);
        return course;
    }
}