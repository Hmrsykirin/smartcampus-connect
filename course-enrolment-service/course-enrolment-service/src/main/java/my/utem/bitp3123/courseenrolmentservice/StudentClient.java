package my.utem.bitp3123.courseenrolmentservice;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class StudentClient {

    private static final String STUDENT_SERVICE_BASE_URL =
            "http://localhost:8081/api/students/";

    private final HttpClient client = HttpClient.newHttpClient();

    public boolean studentExists(String studentId) {

        try {
            String encodedId = URLEncoder
                    .encode(studentId, StandardCharsets.UTF_8)
                    .replace("+", "%20");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(STUDENT_SERVICE_BASE_URL + encodedId))
                    .GET()
                    .build();

            HttpResponse<Void> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.discarding()
            );

            if (response.statusCode() == 200) {
                return true;
            }

            if (response.statusCode() == 404) {
                return false;
            }

            throw new StudentServiceUnavailableException(
                    "Student Profile Service returned status: "
                            + response.statusCode()
            );

        } catch (StudentServiceUnavailableException e) {
            throw e;

        } catch (Exception e) {
            throw new StudentServiceUnavailableException(
                    "Cannot connect to Student Profile Service"
            );
        }
    }
}