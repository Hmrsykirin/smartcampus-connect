package my.utem.bitp3123.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LibraryClient {
    
    private static final String SOAP_URL = "http://localhost:8084/ws/library";
    
    // 1. Borrow a book
    public String borrowBook(String studentId, String bookId) {
        String soapRequest = 
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
            "   <soapenv:Header/>" +
            "   <soapenv:Body>" +
            "      <borrowBook>" +
            "         <studentId>" + studentId + "</studentId>" +
            "         <bookId>" + bookId + "</bookId>" +
            "      </borrowBook>" +
            "   </soapenv:Body>" +
            "</soapenv:Envelope>";
        
        return sendSoapRequest(soapRequest);
    }
    
    // 2. Return a book
    public String returnBook(String bookId) {
        String soapRequest = 
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
            "   <soapenv:Header/>" +
            "   <soapenv:Body>" +
            "      <returnBook>" +
            "         <bookId>" + bookId + "</bookId>" +
            "      </returnBook>" +
            "   </soapenv:Body>" +
            "</soapenv:Envelope>";
        
        return sendSoapRequest(soapRequest);
    }
    
    // 3. Reserve a room
    public String reserveRoom(String studentId, String roomId, String dateTime) {
        String soapRequest = 
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
            "   <soapenv:Header/>" +
            "   <soapenv:Body>" +
            "      <reserveRoom>" +
            "         <studentId>" + studentId + "</studentId>" +
            "         <roomId>" + roomId + "</roomId>" +
            "         <dateTime>" + dateTime + "</dateTime>" +
            "      </reserveRoom>" +
            "   </soapenv:Body>" +
            "</soapenv:Envelope>";
        
        return sendSoapRequest(soapRequest);
    }
    
    // 4. Check booking status
    public String getBookingStatus(String bookingId) {
        String soapRequest = 
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
            "   <soapenv:Header/>" +
            "   <soapenv:Body>" +
            "      <getBookingStatus>" +
            "         <bookingId>" + bookingId + "</bookingId>" +
            "      </getBookingStatus>" +
            "   </soapenv:Body>" +
            "</soapenv:Envelope>";
        
        return sendSoapRequest(soapRequest);
    }
    
    private String sendSoapRequest(String soapRequest) {
        try {
            URL url = new URL(SOAP_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.setRequestProperty("SOAPAction", "");
            connection.setDoOutput(true);
            
            // Send request
            try (OutputStream os = connection.getOutputStream()) {
                os.write(soapRequest.getBytes());
                os.flush();
            }
            
            // Get response
            int responseCode = connection.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    return response.toString();
                }
            } else {
                return "Error: HTTP " + responseCode;
            }
            
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}