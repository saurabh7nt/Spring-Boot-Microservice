# Spring Boot Microservices Project

This repository contains a Spring Boot microservices project that follows a microservices architecture. It includes services for managing users, hotels, and ratings, along with additional components such as Service Registry, Config Server, and API Gateway for enhanced functionality.

## Services

1. **UserService**: Manages user-related operations such as user creation, retrieval, and updates.

2. **HotelService**: Handles hotel-related functionalities, including hotel information and management.

3. **RatingService**: Manages user ratings for hotels, providing functionality to submit and retrieve ratings.

## Additional Components

- **Service Registry (Eureka)**: The Service Registry component helps in service discovery, allowing microservices to register and discover each other.

- **Config Server**: Centralized configuration management using the Config Server ensures that configuration properties are externalized and can be dynamically updated without restarting the services.

- **API Gateway**: The API Gateway acts as a gateway for all incoming requests, providing a single entry point for clients. It can handle authentication, routing, and load balancing.

## Database

The project utilizes MySQL as the database for storing and retrieving data. Ensure you have MySQL set up and configured appropriately before running the application.

## Feign Client

The project incorporates the Feign client for simplified communication between microservices. This allows for seamless interaction between different services within the application.

## Exception Handling

Global exception handling is implemented to ensure robust error handling across the microservices. This enhances the reliability and maintainability of the application.

## Setup Instructions

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/your-repo.git
