package my.utem.bitp3123.librarybookingservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "book", propOrder = { "isbn", "title", "author", "available" })
public class Book {
    @XmlElement(required = true)
    protected String isbn;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String author;
    protected boolean available;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String value) {
        this.isbn = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String value) {
        this.author = value;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean value) {
        this.available = value;
    }
}