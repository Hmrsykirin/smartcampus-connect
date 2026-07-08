package my.utem.bitp3123.client;

public class TestLibraryClient {
    public static void main(String[] args) {
        LibraryClient client = new LibraryClient();
        
        System.out.println("=== Testing Library Service (SOAP) ===\n");
        
        // Test 1: Borrow Book
        System.out.println("📚 Test 1: Borrow Book");
        String result1 = client.borrowBook("S001", "B001");
        System.out.println("Response: " + result1 + "\n");
        
        // Test 2: Reserve Room
        System.out.println("🏠 Test 2: Reserve Room");
        String result2 = client.reserveRoom("S001", "R101", "2026-07-10T14:00:00");
        System.out.println("Response: " + result2 + "\n");
        
        // Test 3: Check Booking Status
        System.out.println("✅ Test 3: Check Booking Status");
        String result3 = client.getBookingStatus("B001-S001-001");
        System.out.println("Response: " + result3 + "\n");
        
        // Test 4: Return Book
        System.out.println("📖 Test 4: Return Book");
        String result4 = client.returnBook("B001");
        System.out.println("Response: " + result4 + "\n");
    }
}