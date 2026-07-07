@echo off
echo ===================================================
echo Creating Directories and Files for Back-End 2 Services
echo ===================================================

:: --- Notification Service ---
echo.
echo [1/3] Generating Notification Service structure...
if not exist "notification-service\src\main\java\my\utem\bitp3123\notificationservice" (
    mkdir "notification-service\src\main\java\my\utem\bitp3123\notificationservice"
)
if not exist "notification-service\src\main\resources" (
    mkdir "notification-service\src\main\resources"
)

type nul > "notification-service\pom.xml"
type nul > "notification-service\src\main\resources\application.properties"
type nul > "notification-service\src\main\java\my\utem\bitp3123\notificationservice\Notification.java"
type nul > "notification-service\src\main\java\my\utem\bitp3123\notificationservice\NotificationController.java"
type nul > "notification-service\src\main\java\my\utem\bitp3123\notificationservice\NotificationServiceApplication.java"
type nul > "notification-service\src\main\java\my\utem\bitp3123\notificationservice\NotificationStore.java"
type nul > "notification-service\src\main\java\my\utem\bitp3123\notificationservice\TcpNotificationServer.java"

:: --- Library Booking Service (SOAP) ---
echo.
echo [2/3] Generating Library Booking Service structure...
if not exist "library-booking-service\src\main\java\my\utem\bitp3123\librarybookingservice" (
    mkdir "library-booking-service\src\main\java\my\utem\bitp3123\librarybookingservice"
)
if not exist "library-booking-service\src\main\resources" (
    mkdir "library-booking-service\src\main\resources"
)

type nul > "library-booking-service\pom.xml"
type nul > "library-booking-service\src\main\resources\application.properties"
type nul > "library-booking-service\src\main\resources\library.xsd"
type nul > "library-booking-service\src\main\java\my\utem\bitp3123\librarybookingservice\Book.java"
type nul > "library-booking-service\src\main\java\my\utem\bitp3123\librarybookingservice\BookBookRequest.java"
type nul > "library-booking-service\src\main\java\my\utem\bitp3123\librarybookingservice\BookBookResponse.java"
type nul > "library-booking-service\src\main\java\my\utem\bitp3123\librarybookingservice\GetBookRequest.java"
type nul > "library-booking-service\src\main\java\my\utem\bitp3123\librarybookingservice\GetBookResponse.java"
type nul > "library-booking-service\src\main\java\my\utem\bitp3123\librarybookingservice\LibraryBookingServiceApplication.java"
type nul > "library-booking-service\src\main\java\my\utem\bitp3123\librarybookingservice\LibraryEndpoint.java"
type nul > "library-booking-service\src\main\java\my\utem\bitp3123\librarybookingservice\WebServiceConfig.java"

:: --- Reporting Analytics Service ---
echo.
echo [3/3] Generating Reporting Analytics Service structure...
if not exist "reporting-analytics-service\src\main\java\my\utem\bitp3123\reportinganalyticsservice" (
    mkdir "reporting-analytics-service\src\main\java\my\utem\bitp3123\reportinganalyticsservice"
)
if not exist "reporting-analytics-service\src\main\resources" (
    mkdir "reporting-analytics-service\src\main\resources"
)

type nul > "reporting-analytics-service\pom.xml"
type nul > "reporting-analytics-service\src\main\resources\application.properties"
type nul > "reporting-analytics-service\src\main\java\my\utem\bitp3123\reportinganalyticsservice\ReportingAnalyticsServiceApplication.java"
type nul > "reporting-analytics-service\src\main\java\my\utem\bitp3123\reportinganalyticsservice\ReportingController.java"
type nul > "reporting-analytics-service\src\main\java\my\utem\bitp3123\reportinganalyticsservice\StudentModel.java"

echo.
echo ===================================================
echo Done! All folders and empty files have been created.
echo ===================================================
pause