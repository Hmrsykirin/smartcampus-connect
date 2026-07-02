package my.utem.bitp3123.courseenrolmentservice;

public class Course {
	
	private String courseCode;
	private String courseName;
	private int capacity;

	public Course() {
	}
	
	public Course(String courseCode, String courseName, int capacity) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.capacity = capacity;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
