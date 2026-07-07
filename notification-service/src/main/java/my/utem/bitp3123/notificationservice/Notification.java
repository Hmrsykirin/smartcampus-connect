package my.utem.bitp3123.notificationservice;

public class Notification {
    private String event;
    private String enrolmentId;
    private String studentId;
    private String courseCode;
    private String enrolmentDate;
    private String receivedAt;

    public Notification() {
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEnrolmentId() {
        return enrolmentId;
    }

    public void setEnrolmentId(String enrolmentId) {
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

    public String getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(String receivedAt) {
        this.receivedAt = receivedAt;
    }
}