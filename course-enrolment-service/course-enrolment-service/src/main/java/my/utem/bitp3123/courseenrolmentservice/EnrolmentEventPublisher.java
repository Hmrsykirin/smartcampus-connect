package my.utem.bitp3123.courseenrolmentservice;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Service
public class EnrolmentEventPublisher {

    private static final String HOST = "localhost";
    private static final int PORT = 9999;

    public void publishEnrolmentSuccess(Enrolment enrolment) {

        String eventMessage = String.format(
                "EVENT:ENROLMENT_SUCCESS:%d:%s:%s:%s",
                enrolment.getEnrolmentId(),
                enrolment.getStudentId(),
                enrolment.getCourseCode(),
                enrolment.getEnrolmentDate()
        );

        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(
                     socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))) {

            out.println(eventMessage);

            String ack = in.readLine();
            System.out.println("Notification Service ACK: " + ack);

        } catch (Exception e) {
            System.out.println(
                    "Notification Service not available. Event not sent: "
                            + e.getMessage()
            );
        }
    }
}