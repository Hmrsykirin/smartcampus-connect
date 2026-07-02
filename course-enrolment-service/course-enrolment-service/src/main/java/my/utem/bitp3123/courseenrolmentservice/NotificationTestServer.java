package my.utem.bitp3123.courseenrolmentservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NotificationTestServer {

    private static final int PORT = 9999;

    public static void main(String[] args) throws Exception {

        System.out.println("Notification Test Server running on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket socket = serverSocket.accept();

                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(
                             socket.getOutputStream(), true)) {

                    String message = in.readLine();

                    System.out.println("Received event: " + message);

                    out.println("ACK:EVENT_RECEIVED");
                }
            }
        }
    }
}