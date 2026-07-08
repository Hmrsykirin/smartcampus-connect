package my.utem.bitp3123.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class NotificationInteractiveClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9999;
    
    public static void main(String[] args) {
        System.out.println("=== Notification Service Interactive Client ===");
        System.out.println("Commands:");
        System.out.println("  enrol <studentId> <courseCode>");
        System.out.println("  book <studentId> <bookId>");
        System.out.println("  room <studentId> <roomId>");
        System.out.println("  quit");
        System.out.println("================================================");
        
        try (Scanner scanner = new Scanner(System.in);
             Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()))) {
            
            while (true) {
                System.out.print("\n> ");
                String command = scanner.nextLine().trim();
                
                if (command.equalsIgnoreCase("quit")) {
                    System.out.println("Goodbye!");
                    break;
                }
                
                String event = parseCommand(command);
                if (event != null) {
                    System.out.println("📤 Sending: " + event);
                    out.println(event);
                    
                    String response = in.readLine();
                    System.out.println("📥 " + response);
                } else {
                    System.out.println("❌ Invalid command. Try again.");
                }
            }
            
        } catch (Exception e) {
            System.err.println("❌ Connection error: " + e.getMessage());
        }
    }
    
    private static String parseCommand(String command) {
        String[] parts = command.split(" ");
        String timestamp = LocalDateTime.now().toString();
        
        switch (parts[0].toLowerCase()) {
            case "enrol":
                if (parts.length >= 3) {
                    return "EVENT:ENROLMENT_SUCCESS:1:" + parts[1] + ":" + parts[2] + ":" + timestamp;
                }
                break;
            case "book":
                if (parts.length >= 3) {
                    return "EVENT:BOOK_BORROWED:1:" + parts[1] + ":" + parts[2] + ":" + timestamp;
                }
                break;
            case "room":
                if (parts.length >= 3) {
                    return "EVENT:ROOM_RESERVED:1:" + parts[1] + ":" + parts[2] + ":" + timestamp;
                }
                break;
        }
        return null;
    }
}