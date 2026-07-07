package my.utem.bitp3123.librarybookingservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "isbn" })
@XmlRootElement(name = "getBookRequest", namespace = "http://utem.my/bitp3123/library")
public class GetBookRequest {

    @XmlElement(required = true, namespace = "http://utem.my/bitp3123/library")
    protected String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String value) {
        this.isbn = value;
    }
}