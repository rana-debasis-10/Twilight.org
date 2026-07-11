# Twilight

> **A production-oriented multi-outlet food delivery platform built with Spring Boot, PostgreSQL, Redis, Kafka, Docker and Flutter.**

Twilight is a backend-first food delivery platform inspired by modern delivery applications such as Swiggy, Zomato and Uber Eats. The project is designed with scalability, maintainability and real-world system design principles in mind.

This project is currently under active development and is being built from scratch as a personal learning project.

---

# Features

## Authentication & Security

- JWT Authentication
- OTP based Login
- Role Based Authorization
- Secure REST APIs
- Password Encryption
- Authentication Filters
- Global Exception Handling
- Validation using Jakarta Validation

---

## Business Model

Twilight supports multiple user roles.

- Customer
- Merchant
- Restaurant Manager
- Delivery Partner
- Administrator

A Merchant can own one* Restaurant. (* Planning to convert to multi restaurant model )

Each Restaurant can contain multiple Outlets.

Each Outlet is managed independently by a Restaurant Manager.

---

## Customer Features

- Customer Registration
- Get Nearest Outlets
- OTP Login
- Address Management
- Browse Restaurants
- Browse Menu
- Place Orders
- Online Payments
- Order History
- Order Tracking

---

## Merchant Features

- Restaurant Management
- Outlet Management
- Product Management
- Food Availability Management
- Manager Assignment

---

## Delivery Partner Features

- Driver Registration
- Availability Management
- Location Updates
- Real-time Order Notifications *(In Progress)*
- Order Acceptance *(In Progress)*

---

## Payment

- Razorpay Integration
- Secure Payment Verification
- Webhook Verification
- Online Payment Support

---

## Real-Time Features

- WebSocket Infrastructure
- Live Driver Notifications *(In Progress)*
- Dynamic Driver Allocation *(In Progress)*

---

## Distributed Systems

- Apache Kafka Event Processing
- Redis Caching
- Redis GEO *(In Progress)*
- Event Driven Architecture

---

## Storage

- PostgreSQL
- PostGIS
- Redis
- MinIO (S3 Compatible Object Storage)

---

## Backend Technologies

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MapStruct
- Lombok
- Docker
- Docker Compose

---

# Architecture

```
                          Flutter Application
                                  │
                         HTTPS / WebSocket
                                  │
                     Spring Boot Backend API
                                  │
        ┌──────────────┬──────────────┬──────────────┐
        │              │              │              │
   PostgreSQL       Redis            Kafka          MinIO
     + PostGIS     Cache      Event Bus      Object Storage
                 + RedisGeo
```

The application follows a layered architecture.

```
Controller
      │
Service
      │
Repository
      │
Database
```

Business events are processed asynchronously using Apache Kafka where appropriate.

---

# Current Modules

- Authentication
- Authorization
- Merchant Management
- Restaurant Management
- Outlet Management
- Product Management
- Food Management
- Customer Management
- Address Management
- Order Management
- Payment Integration
- Payment Verification
- Webhook Processing
- SMS Gateway Integration
- Logging
- Exception Handling

---

# Project Structure

```
src
 ├── annotations(Custom annotations for validation)
 ├── configurations(Context configuration files)
 ├── controllers/endPoint (HTTP APIs)
 ├── services (Services)
 ├── serviceImpls (Actual Implementation of Service)
 ├── objects (Database Objects)
 ├── dataTransfarObjects 
 ├── filters (Security Filters etc.)
 ├── development (Tools required during development) 
 ├── validators (Validating specific inputs)
 ├── Interceptors (Interceptors)
 ├── managers (Session Managers)
 ├── handlers (Global Exceptions and Connection Handler)
 ├── repositories (Database connectors)
 ├── mapper (DTO to Database object mapper)
 ├── exceptions (Business exceptions)
 ├── types (All type of fixed types/enums)
 └── utils (Maintaining All Utility objects and components)
 
```

---

# Technology Stack

| Category | Technologies             |
|-----------|--------------------------|
| Language | Java 21                  |
| Framework | Spring Boot              |
| Security | Spring Security, JWT     |
| Database | PostgreSQL + PostGIS     |
| Cache | Redis + RedisGEO         |
| Messaging | Apache Kafka + Websocket |
| Storage | MinIO                    |
| Payment | Razorpay                 |
| Deployment | Docker, Docker Compose   |
| Mobile | Flutter                  |

---

# Running the Project

Clone the repository

```bash
git clone https://github.com/rana-debasis-10/Twilight.org.git

cd Twilight.org
```

Create an `.env` file containing all required environment variables.

Build

```bash
./gradlew build
```

Run

```bash
docker compose up -d
```

or

```bash
./gradlew bootRun
```

---

# Environment Variables

The project uses environment variables for secure configuration.

Examples include

- Database Credentials
- JWT Secret
- Razorpay Keys
- Kafka Configuration
- Redis Configuration
- MinIO Credentials
- SMS Gateway Configuration

---

# Roadmap

## Completed

- JWT Authentication
- OTP Login
- Merchant Management
- Restaurant Management
- Outlet Management
- Product Management
- Payment Integration
- Webhook Verification
- Docker Deployment
- Kafka Integration
- Redis Integration
- MinIO Integration
- Flutter Client

## Currently Developing (close to complete)

- Dynamic Driver Allocation
- Redis GEO Driver Search
- Live Driver Notifications
- WebSocket Communication
- Live Driver Tracking

## Planned


- Push Notifications
- Recommendation System
- CI/CD Pipeline
- Kubernetes Deployment
- Prometheus & Grafana Monitoring
- AWS Deployment

---

# Related Projects

## Flutter Client

*Coming Soon*

## SMS Gateway

*Coming Soon*

---

# Author

**Debasis Rana**

B.Tech Information Technology

University of Calcutta

Backend Engineering • Distributed Systems • Machine Learning

GitHub

https://github.com/rana-debasis-10

LinkedIn

https://www.linkedin.com/in/debasis-rana-7556aa300/

---

# Project Status

Twilight is under active development.

The objective of this project is not only to build a complete food delivery platform but also to explore production-grade backend engineering concepts including distributed systems, asynchronous messaging, caching, real-time communication, cloud-native development, and scalable software architecture.