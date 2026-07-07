package my.utem.bitp3123.librarybookingservice;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "book" })
@XmlRootElement(name = "getBookResponse", namespace = "http://utem.my/bitp3123/library")
public class GetBookResponse {
    @XmlElement(required = true, namespace = "http://utem.my/bitp3123/library")
    protected Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book value) {
        this.book = value;
    }
}