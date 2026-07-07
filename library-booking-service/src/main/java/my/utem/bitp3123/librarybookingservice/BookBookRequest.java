package my.utem.bitp3123.librarybookingservice;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "isbn", "studentId" })
@XmlRootElement(name = "bookBookRequest", namespace = "http://utem.my/bitp3123/library")
public class BookBookRequest {
    @XmlElement(required = true, namespace = "http://utem.my/bitp3123/library")
    protected String isbn;
    @XmlElement(required = true, namespace = "http://utem.my/bitp3123/library")
    protected String studentId;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String value) {
        this.isbn = value;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String value) {
        this.studentId = value;
    }
}