package my.utem.bitp3123.courseenrolmentservice;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/enrolments")
public class EnrolmentController {

    private final EnrolmentService service;

    EnrolmentController(EnrolmentService service) {
        this.service = service;
    }

    // GET /api/enrolments
    @GetMapping
    public List<Enrolment> getAll() {
        return service.findAll();
    }

    // POST /api/enrolments
    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody EnrolmentRequest request) {

        if (request.getStudentId() == null ||
            request.getStudentId().isBlank() ||
            request.getCourseCode() == null ||
            request.getCourseCode().isBlank()) {

            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "message",
                            "studentId and courseCode are required"
                    ));
        }

        try {
            Enrolment created = service.enrol(request);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(created);

        } catch (StudentServiceUnavailableException e) {

            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of("message", e.getMessage()));

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));

        } catch (IllegalStateException e) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}