package my.utem.bitp3123.librarybookingservice;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Endpoint
public class LibraryEndpoint {
    private static final String NAMESPACE_URI = "http://utem.my/bitp3123/library";

    private final Map<String, Book> booksInventory = new ConcurrentHashMap<>();

    public LibraryEndpoint() {
        // Mock library catalogs
        booksInventory.put("978-0134494166",
                createBook("978-0134494166", "Clean Architecture", "Robert C. Martin", true));
        booksInventory.put("978-1491950356",
                createBook("978-1491950356", "Designing Data-Intensive Applications", "Martin Kleppmann", true));
        booksInventory.put("978-0321200686",
                createBook("978-0321200686", "Enterprise Integration Patterns", "Gregor Hohpe", false));
    }

    private Book createBook(String isbn, String title, String author, boolean available) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setAvailable(available);
        return book;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBook(@RequestPayload GetBookRequest request) {
        GetBookResponse response = new GetBookResponse();
        Book book = booksInventory.get(request.getIsbn());
        if (book != null) {
            response.setBook(book);
        } else {
            Book empty = new Book();
            empty.setIsbn(request.getIsbn());
            empty.setTitle("Unknown Book");
            empty.setAuthor("N/A");
            empty.setAvailable(false);
            response.setBook(empty);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "bookBookRequest")
    @ResponsePayload
    public BookBookResponse bookBook(@RequestPayload BookBookRequest request) {
        BookBookResponse response = new BookBookResponse();
        Book book = booksInventory.get(request.getIsbn());

        if (book == null) {
            response.setSuccess(false);
            response.setMessage("Book not found in library registry.");
            response.setBookingId("");
        } else if (!book.isAvailable()) {
            response.setSuccess(false);
            response.setMessage("Book already reserved or checked out.");
            response.setBookingId("");
        } else {
            book.setAvailable(false); // Book reserved
            response.setSuccess(true);
            response.setMessage("Book successfully reserved for student: " + request.getStudentId());
            response.setBookingId(UUID.randomUUID().toString());
        }
        return response;
    }
}