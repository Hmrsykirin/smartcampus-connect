# SmartCampus Connect

This is our group project for **BITP3123 Distributed Application Development**.

SmartCampus Connect is a distributed backend system for a university campus. The main idea of this project is to separate the campus functions into different services, where each service has its own responsibility and can run independently.

The system focuses more on backend development, service communication, REST API, SOAP, message passing, and testing using Postman. A full frontend interface is not included because the project requirement says that front-end design is not the main grading component.

---

## Project Topic

**SmartCampus Connect** is a distributed backend platform that provides several campus services, such as:

- Student profile management
- Course enrolment
- Notification
- Library or booking service
- Reporting and analytics

Each service is developed separately and communicates with other services when needed.

---

## System Architecture

SmartCampus Connect uses a service-based architecture. Each main function is separated into its own backend service. The services run on different ports and communicate with each other using REST API or socket message passing.

For testing, we use Postman as the client.

```
Postman / Client
      |
      | REST API request
      |
      v
Student Profile Service
Port: 8081
- Manage student information
- Create, view, update, and delete student records


Postman / Client
      |
      | REST API request
      |
      v
Course Enrolment Service
Port: 8082
- Manage courses
- Enrol students into courses
- Check course capacity
- Prevent duplicate enrolment


Course Enrolment Service
      |
      | REST API call
      | Check student exists
      v
Student Profile Service


Course Enrolment Service
      |
      | Socket message
      | Send enrolment success event
      v
Notification Service
Port: 9999 / 8083


Library / Booking Service
- Handles book loans and room booking
- Uses SOAP/WSDL for legacy system simulation


Reporting / Analytics Service
- Produces report and summary data
- Reads data from other services
```

In this architecture, the Student Profile Service and Course Enrolment Service are independent services. The Course Enrolment Service depends on the Student Profile Service because it needs to check whether a student exists before the enrolment process is completed.

After a successful enrolment, the Course Enrolment Service sends an event message to the Notification Service. This shows message passing between services. The Library / Booking Service uses SOAP/WSDL to represent a legacy system, while the Reporting / Analytics Service is used to produce summary reports.

---

## Technologies Used

The technologies and tools used in this project are:

- Java 17
- Spring Boot
- Maven
- REST API
- SOAP / WSDL
- TCP Socket Message Passing
- Postman
- Eclipse IDE for Enterprise Java Developers
- GitHub

---

## Services and Ports

| Service | Port |
|---|---:|
| Student Profile Service | 8081 |
| Course Enrolment Service | 8082 |
| Notification Service | 9999 / 8083 |
| Library / Booking Service | 8084 |
| Reporting / Analytics Service | 8085 |

The ports are separated so that all services can run at the same time. This is important because the services need to communicate with each other.

---

## Project Modules

### 1. Student Profile Service

The Student Profile Service is used to manage student information.

Main functions:

- Add new student
- View all students
- View student by ID
- Update student information
- Delete student

Base URL:

```text
http://localhost:8081/api/students
```

---

### 2. Course Enrolment Service

The Course Enrolment Service is used to manage courses and student enrolment.

Main functions:

- View all courses
- View course by course code
- Add new course
- Enrol student into a course
- View all enrolments
- Check course capacity
- Prevent duplicate enrolment
- Check student existence by calling Student Profile Service
- Send enrolment success event to Notification Service

Base URL:

```text
http://localhost:8082
```

---

### 3. Notification Service

The Notification Service receives event messages from other services.

For example, after a student is successfully enrolled into a course, the Course Enrolment Service sends an event message to the Notification Service.

Example event message:

```text
EVENT:ENROLMENT_SUCCESS:1:S001:BITP3123:2026-07-02T15:30:00
```

---

### 4. Library / Booking Service

The Library / Booking Service handles library-related functions.

Example functions:

- Borrow book
- Return book
- Reserve discussion room
- Check booking status

This service includes at least one SOAP/WSDL operation to simulate an old legacy system.

---

### 5. Reporting / Analytics Service

The Reporting / Analytics Service is used to generate simple reports and summaries.

Example reports:

- Enrolment count by course
- Enrolment count by programme
- Student activity summary
- Library booking summary

---

## API Endpoints

### Student Profile Service

Base URL:

```text
http://localhost:8081/api/students
```

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| POST | `/api/students` | Create new student |
| PUT | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Delete student |

---

### Course API

Base URL:

```text
http://localhost:8082/api/courses
```

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/courses` | Get all courses |
| GET | `/api/courses/{courseCode}` | Get course by code |
| POST | `/api/courses` | Create new course |

---

### Enrolment API

Base URL:

```text
http://localhost:8082/api/enrolments
```

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/enrolments` | Get all enrolments |
| POST | `/api/enrolments` | Enrol student into course |

---

## How to Run the Project

### Step 1: Run Student Profile Service

Open the Student Profile Service project in Eclipse.

Run this file:

```text
StudentProfileServiceApplication.java
```

Expected console output:

```text
Tomcat started on port 8081
```

Test using Postman:

```http
GET http://localhost:8081/api/students
```

---

### Step 2: Run Course Enrolment Service

Open the Course Enrolment Service project in Eclipse.

Run this file:

```text
CourseEnrolmentServiceApplication.java
```

Expected console output:

```text
Tomcat started on port 8082
```

Test using Postman:

```http
GET http://localhost:8082/api/courses
```

---

### Step 3: Run Notification Test Server

For testing the message passing part, run:

```text
NotificationTestServer.java
```

Expected console output:

```text
Notification Test Server running on port 9999
```

Then create a new enrolment in Postman. If the event is sent successfully, the Notification Test Server console will show the event message.

---

## Postman Testing

Postman is used to test all REST APIs.

The exported Postman collection is placed inside this folder:

```text
postman/
```
---

## Group Members and Responsibilities

| Name | Matric Number | Responsibility |
|---|---|---|
| Nur Humaira Shakirin Binti Shamsudin | B032410971 | Student Profile Service and Course Enrolment Service |
| Intan Fatihah Natasha Binti Ismadi | B032410887 | Notification Services, Library/Booking Services and Reporting/Analytics Service |
| Name | MatricNo. | Task |
| Name | MatricNo. | Task |
| Name | MatricNo. | Task |

---

## Current Progress

### Completed

- Student Profile Service basic REST API
- Course Enrolment Service basic REST API
- Service-to-service validation between Course Enrolment and Student Profile
- Course capacity checking
- Duplicate enrolment checking
- Basic socket message event to Notification Test Server
- Postman testing for completed services

### In Progress

- Notification Service
- Library / Booking Service with SOAP
- Reporting / Analytics Service
- Full integration testing
- Final presentation preparation

---
