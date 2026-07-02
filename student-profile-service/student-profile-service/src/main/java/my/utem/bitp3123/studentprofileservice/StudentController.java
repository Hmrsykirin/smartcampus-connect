package my.utem.bitp3123.studentprofileservice;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	private final StudentService service;
	
	StudentController(StudentService service){
		this.service = service;
	}
	
	@GetMapping
	public List<Student> getAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getOne(
			@PathVariable String id){
		return service.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Student> create(
			@RequestBody Student student){
		if (student.getName() == null || student.getName().isBlank()) {
			return ResponseEntity.badRequest().build();
		}
		
		Student created = service.create(student);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> update(
			@PathVariable String id, @RequestBody Student student){
		return service.update(id, student)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
			@PathVariable String id){
		return service.delete(id)
				? ResponseEntity.noContent().build()
						:ResponseEntity.notFound().build();
	}

}
