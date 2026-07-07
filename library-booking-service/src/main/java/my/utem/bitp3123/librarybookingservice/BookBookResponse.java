package my.utem.bitp3123.librarybookingservice;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "success", "message", "bookingId" })
@XmlRootElement(name = "bookBookResponse", namespace = "http://utem.my/bitp3123/library")
public class BookBookResponse {
    @XmlElement(namespace = "http://utem.my/bitp3123/library")
    protected boolean success;
    @XmlElement(required = true, namespace = "http://utem.my/bitp3123/library")
    protected String message;
    @XmlElement(required = true, namespace = "http://utem.my/bitp3123/library")
    protected String bookingId;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean value) {
        this.success = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String value) {
        this.bookingId = value;
    }
}