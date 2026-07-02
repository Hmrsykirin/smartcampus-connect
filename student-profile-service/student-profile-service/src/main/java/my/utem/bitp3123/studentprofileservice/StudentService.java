package my.utem.bitp3123.studentprofileservice;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {
	
	private final List<Student> students = new ArrayList<>(List.of(
			new Student("S001", "Muhammad", "BITS", "muhammad@utem.edu.my", 2),
			new Student("S002", "Noor", "BITC", "noor@utem.edu.my", 2),
			new Student("S003", "Aminah", "BITZ", "aminah@utem.edu.my", 2)
			));
	private int nextId = 4;
	
	public List<Student> findAll() { return students; }
	
	public Optional<Student> findById(String studentId) {
		return students.stream().filter(s -> s.getStudentId().equals(studentId)).findFirst();
	}

	public Student create(Student student) {
		student.setStudentId("S" + String.format("%03d", nextId++));
		students.add(student);
		return student;
	}
	
	public Optional<Student> update(String studentId, Student updated) {
		return findById(studentId).map(existing -> {
			existing.setName(updated.getName());
			existing.setProgramme(updated.getProgramme());
			existing.setEmail(updated.getEmail());
			existing.setYear(updated.getYear());
			return existing;
		});
	}
	
	public boolean delete(String studentId) {
		return students.removeIf(s -> s.getStudentId().equals(studentId));
	}
}
