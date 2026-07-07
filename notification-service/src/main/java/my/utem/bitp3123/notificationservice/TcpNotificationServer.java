package my.utem.bitp3123.notificationservice;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

@Component
public class TcpNotificationServer {

    private static final int PORT = 9999;
    private final NotificationStore notificationStore;
    private ServerSocket serverSocket;
    private boolean running = false;

    public TcpNotificationServer(NotificationStore notificationStore) {
        this.notificationStore = notificationStore;
    }

    @PostConstruct
    public void startServer() {
        running = true;
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                System.out.println("TCP Notification Server listening on port " + PORT);
                while (running) {
                    Socket socket = serverSocket.accept();
                    new Thread(() -> handleClient(socket)).start();
                }
            } catch (Exception e) {
                if (running) {
                    System.err.println("TCP Server Error: " + e.getMessage());
                }
            }
        }).start();
    }

    private void handleClient(Socket socket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String message = in.readLine();
            System.out.println("TCP Server received: " + message);

            if (message != null && message.startsWith("EVENT:ENROLMENT_SUCCESS:")) {
                String[] parts = message.split(":");
                if (parts.length >= 6) {
                    Notification notification = new Notification();
                    notification.setEvent(parts[1]);
                    notification.setEnrolmentId(parts[2]);
                    notification.setStudentId(parts[3]);
                    notification.setCourseCode(parts[4]);
                    notification.setEnrolmentDate(parts[5]);
                    notification.setReceivedAt(LocalDateTime.now().toString());

                    notificationStore.addNotification(notification);
                }
            }
            out.println("ACK:EVENT_RECEIVED");
        } catch (Exception e) {
            System.err.println("Error handling client connection: " + e.getMessage());
        }
    }

    @PreDestroy
    public void stopServer() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (Exception e) {
            System.err.println("Error shutting down TCP server: " + e.getMessage());
        }
    }
}