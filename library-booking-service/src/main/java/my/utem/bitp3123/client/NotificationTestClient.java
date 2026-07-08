package my.utem.bitp3123.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NotificationTestClient {
    
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9999;
    
    public static void main(String[] args) {
        System.out.println("=== Testing Notification Service (TCP Socket) ===\n");
        
        // Test 1: Enrollment Success
        testEnrolmentSuccess();
        
        // Test 2: Book Borrowed
        testBookBorrowed();
        
        // Test 3: Room Reserved
        testRoomBooked();
    }
    
    public static void testEnrolmentSuccess() {
        String event = "EVENT:ENROLMENT_SUCCESS:1:S001:BITP3123:2026-07-08T10:30:00";
        sendEvent(event);
    }
    
    public static void testBookBorrowed() {
        String event = "EVENT:BOOK_BORROWED:1:S001:B001:2026-07-08T11:00:00";
        sendEvent(event);
    }
    
    public static void testRoomBooked() {
        String event = "EVENT:ROOM_RESERVED:1:S001:R101:2026-07-08T14:00:00";
        sendEvent(event);
    }
    
    private static void sendEvent(String eventMessage) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()))) {
            
            System.out.println("📤 Sending: " + eventMessage);
            out.println(eventMessage);
            
            String response = in.readLine();
            System.out.println("📥 Response: " + response);
            System.out.println();
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            System.err.println("Make sure NotificationTestServer is running on port 9999\n");
        }
    }
}