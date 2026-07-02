package my.utem.bitp3123.studentprofileservice;

public class Student {
	
	private String studentId;
	private String name;
	private String programme;
	private String email;
	private int year;

	public Student() {}
	
	public Student(String studentId, String name, String programme, String email, int year){
		this.studentId = studentId;
		this.name = name;
		this.programme = programme;
		this.email = email;
		this.year = year;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

}
