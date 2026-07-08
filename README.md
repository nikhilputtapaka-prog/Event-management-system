# Event Management System

## Overview

The Event Management System is a RESTful backend application developed using Spring Boot. It enables organizers to create and manage events while allowing participants to register for available events. The application includes event scheduling, participant management, registration management, automatic event status updates, validation, exception handling, and database persistence.

---

## Features

### Event Management

* Create a new event
* View all events
* View event by ID
* Update event details
* Delete an event
* Search events by city
* Search events by category

### Participant Management

* Register a participant
* View all participants
* View participant by ID
* Update participant information (Partial Update)
* Delete participant

### Registration Management

* Register a participant for an event
* Prevent duplicate registrations
* Cancel registration
* View registrations by participant
* View registrations by event

### Automatic Event Status Update

The application automatically updates event status using Spring Scheduler.

* UPCOMING
* ONGOING
* COMPLETED
* CANCELLED

---

## Business Rules

* A participant cannot register if the event has no available seats.
* Available seats decrease after successful registration.
* Available seats increase when a registration is cancelled.
* Event status is updated automatically based on the current date and time.
* Cancelled events are excluded from automatic status updates.

---

## Technologies Used

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Lombok
* Spring Validation
* Loggers
* Spring Scheduler
* Swagger / OpenAPI
* Postman
* Git & GitHub

---

## Project Structure

```text
src
├── controller
├── service
│   └── implementation
├── repository
├── entity
├── dto
├── enums
├── scheduler
└── EventManagementApplication
```

---

## Entity Relationship

### Event

* Event ID
* Title
* Description
* Category
* Venue
* City
* Event Date
* Start Time
* End Time
* Capacity
* Available Seats
* Ticket Price
* Status
* Created At

### Participant

* Participant ID
* Name
* Email
* Phone
* Age
* Gender
* Registered At

### Registration

* Registration ID
* Registration Date
* Ticket Count
* Payment Status
* Registration Status
* Event
* Participant

---

## API Endpoints

### Event APIs

| Method | Endpoint                                | Description        |
| ------ | ----------------------------------------| ------------------ |
| POST   | `/events/createevent`                   | Create Event       |
| GET    | `/events/allevents`                     | Get All Events     |
| GET    | `/events/eventid/{id}`                  | Get Event By ID    |
| PATCH  | `events/updateevents/{id}`              | Update Event       |
| DELETE | `/events/deleteevent/{id}`              | Delete Event       |
| GET    | `/events/searchbycity?city=`            | Search By City     |
| GET    | `/events/searchbycategory/{category}`   | Search By Category |

---

### Participant APIs

| Method | Endpoint                                 | Description           |
| ------ | ---------------------------------------- | --------------------- |
| POST   | `participant/registerparticipant`        | Create Participant    |
| GET    | `/participant/getallparticipants`        | Get All Participants  |
| GET    | `/participant/getbyid/{id}`              | Get Participant By ID |
| PATCH  | `/participant/updatebyid/{id}`           | Update Participant    |
| DELETE | `/participant/deleteparticipant/{id}`    | Delete Participant    |

---

### Registration APIs

| Method | Endpoint                                     | Description                      |
| ------ | -------------------------------------------- | -------------------------------- |
| POST   | `/registration/registerevent`                | Register Participant             |
| GET    | `/registration/getallregistrations`          | Get All Registrations            |
| GET    | `/registration/getbyid/{id}`                 | Get Registration By ID           |
| POST   | `/registration/cancelregistration/`          | Cancel Registration              |
| GET    | `/registration/getbyid/{eventId}`            | Get Registrations By Event       |
| GET    | `/registration/getbyeventid/{eventId}`       | Get Registrations By Event ID    |

---

## Automatic Scheduler

The application uses Spring Scheduler to update event statuses automatically.

```java
@Scheduled(cron = "0 */5 * * * *")
```

The scheduler periodically checks all active events and updates their status based on the current date and time.

---

## Validation

The project uses Bean Validation to validate user input, including:

* Required fields
* Email format validation
* Phone number validation

---


## Database

MySQL is used as the relational database, and Hibernate automatically manages the entity mappings.

---

## API Documentation

Swagger/OpenAPI is integrated to test and explore all REST APIs through the browser.

---

## Future Enhancements

* JWT Authentication & Authorization
* Role-Based Access Control (Admin/Organizer/Participant)
* Email Notifications
* Payment Gateway Integration
* Event Image Upload
* QR Code Based Event Check-In
* Analytics Dashboard
* Docker Deployment
* Microservices Architecture

---

## Learning Outcomes

This project helped strengthen practical knowledge of:

* Spring Boot
* REST API Development
* Spring Data JPA
* Hibernate Relationships
* DTO Design
* Exception Handling
* Validation
* Scheduler
* Layered Architecture
* CRUD Operations
* Git & GitHub
* API Testing using Postman
* Database Design

---

## Author

**Nikhil Yadav**

Java Backend Developer

GitHub: **https://github.com/nikhilputtapaka-prog/Event-management-system**

LinkedIn: **https://www.linkedin.com/in/puttapaka-nikhil**
