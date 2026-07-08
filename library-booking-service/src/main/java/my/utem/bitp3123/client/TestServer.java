package my.utem.bitp3123.client;

import java.io.*;
import java.net.*;

public class TestServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9999);
            System.out.println("✅ SERVER STARTED ON PORT 9999!");
            System.out.println("Server is running... Waiting for messages...");
            
            while (true) {
                // Accept client connection
                Socket clientSocket = server.accept();
                System.out.println("📥 Client connected!");
                
                // Read message
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
                
                String message = in.readLine();
                if (message != null && !message.isEmpty()) {
                    System.out.println("📩 Received: " + message);
                    
                    // Send response back to client
                    out.println("EVENT_RECEIVED: Notification processed successfully");
                    System.out.println("📤 Sent response to client");
                }
                
                clientSocket.close();
                System.out.println("📥 Client disconnected");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}