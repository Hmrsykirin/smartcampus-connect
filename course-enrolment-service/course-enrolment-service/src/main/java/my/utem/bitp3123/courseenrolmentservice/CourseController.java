package my.utem.bitp3123.courseenrolmentservice;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> getAll() {
        return service.findAll();
    }

    @GetMapping("/{courseCode}")
    public ResponseEntity<Course> getOne(@PathVariable String courseCode) {
        return service.findByCode(courseCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {

        if (course.getCourseCode() == null ||
            course.getCourseCode().isBlank()) {

            return ResponseEntity.badRequest().build();
        }

        Course created = service.create(course);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }
}