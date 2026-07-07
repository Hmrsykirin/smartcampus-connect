package my.utem.bitp3123.reportinganalyticsservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String STUDENT_SERVICE_URL = "http://localhost:8081/api/students";
    private static final String ENROLMENT_SERVICE_URL = "http://localhost:8082/api/enrolments";

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboardData() {
        Map<String, Object> report = new LinkedHashMap<>();
        List<StudentModel> students = new ArrayList<>();

        // Fetch students
        try {
            StudentModel[] response = restTemplate.getForObject(STUDENT_SERVICE_URL, StudentModel[].class);
            if (response != null) {
                students = Arrays.asList(response);
            }
        } catch (Exception e) {
            System.err.println(
                    "Student Profile Service unavailable, running fallback aggregation. Error: " + e.getMessage());
            // Fallback mock students if service is offline
            students = getFallbackStudents();
        }

        // Fetch enrolments count
        int totalEnrolments = 0;
        try {
            Object[] enrolments = restTemplate.getForObject(ENROLMENT_SERVICE_URL, Object[].class);
            if (enrolments != null) {
                totalEnrolments = enrolments.length;
            }
        } catch (Exception e) {
            System.err.println(
                    "Course Enrolment Service unavailable or /api/enrolments not present. Using mock count. Error: "
                            + e.getMessage());
            totalEnrolments = (int) (students.size() * 0.6); // Approximate 60% enrolment rate
        }

        // Process data
        int totalStudents = students.size();
        Map<String, Integer> programmeCount = new HashMap<>();
        Map<String, Integer> yearCount = new HashMap<>();

        for (StudentModel student : students) {
            String prog = student.getProgramme() != null ? student.getProgramme() : "Unknown";
            programmeCount.put(prog, programmeCount.getOrDefault(prog, 0) + 1);

            String yrStr = "Year " + student.getYear();
            yearCount.put(yrStr, yearCount.getOrDefault(yrStr, 0) + 1);
        }

        report.put("totalStudents", totalStudents);
        report.put("totalEnrolments", totalEnrolments);
        report.put("enrolmentRatio",
                totalStudents > 0 ? String.format("%.1f%%", ((double) totalEnrolments / totalStudents) * 100) : "0%");
        report.put("programmeDistribution", programmeCount);
        report.put("yearDistribution", yearCount);

        return report;
    }

    private List<StudentModel> getFallbackStudents() {
        List<StudentModel> mock = new ArrayList<>();
        mock.add(new StudentModel() {
            {
                setStudentId("B032110001");
                setName("Ahmad");
                setProgramme("BITD");
                setYear(3);
            }
        });
        mock.add(new StudentModel() {
            {
                setStudentId("B032110002");
                setName("Chong");
                setProgramme("BITS");
                setYear(2);
            }
        });
        mock.add(new StudentModel() {
            {
                setStudentId("B032110003");
                setName("Muthu");
                setProgramme("BITI");
                setYear(4);
            }
        });
        mock.add(new StudentModel() {
            {
                setStudentId("B032110004");
                setName("Sarah");
                setProgramme("BITD");
                setYear(1);
            }
        });
        return mock;
    }
}