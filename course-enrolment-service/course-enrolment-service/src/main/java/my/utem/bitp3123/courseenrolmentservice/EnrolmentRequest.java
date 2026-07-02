package my.utem.bitp3123.courseenrolmentservice;

public class EnrolmentRequest {
	
	private String studentId;
	private String courseCode;

	public EnrolmentRequest() {
	}
	
	public EnrolmentRequest(String studentId, String courseCode) {
		this.studentId = studentId;
		this.courseCode = courseCode;
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

}
