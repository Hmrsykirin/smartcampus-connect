@echo off
echo ===================================================
echo Starting All SmartCampus Connect Services...
echo ===================================================

echo [1/5] Starting Student Profile Service (8081)...
start "Student Profile Service" cmd /k "cd student-profile-service\student-profile-service && mvn spring-boot:run"

echo [2/5] Starting Course Enrolment Service (8082)...
start "Course Enrolment Service" cmd /k "cd course-enrolment-service\course-enrolment-service && mvn spring-boot:run"

echo [3/5] Starting Notification Service (8085 / 9999)...
start "Notification Service" cmd /k "cd notification-service && mvn spring-boot:run"

echo [4/5] Starting Library Booking Service (8083)...
start "Library Booking Service" cmd /k "cd library-booking-service && mvn spring-boot:run"

echo [5/5] Starting Reporting & Analytics Service (8084)...
start "Reporting Service" cmd /k "cd reporting-analytics-service && mvn spring-boot:run"

echo ===================================================
echo All 5 services have been launched!
echo ===================================================
pause