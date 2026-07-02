package my.utem.bitp3123.courseenrolmentservice;

public class Enrolment {
	
	private int enrolmentId;
	private String studentId;
	private String courseCode;
	private String enrolmentDate;

	public Enrolment() {
	}
	
	public Enrolment(int enrolmentId, String studentId, String courseCode, String enrolmentDate) {
		this.enrolmentId = enrolmentId;
		this.studentId = studentId;
		this.courseCode = courseCode;
		this.enrolmentDate = enrolmentDate;
	}

	public int getEnrolmentId() {
		return enrolmentId;
	}

	public void setEnrolmentId(int enrolmentId) {
		this.enrolmentId = enrolmentId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getEnrolmentDate() {
		return enrolmentDate;
	}

	public void setEnrolmentDate(String enrolmentDate) {
		this.enrolmentDate = enrolmentDate;
	}

}
