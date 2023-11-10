# Spring Boot Microservices Project

This repository contains a Spring Boot microservices project that includes services for managing users, hotels, and ratings. The microservices architecture allows for modular development, scalability, and easy maintenance.

## Services

1. **UserService**: Manages user-related operations such as user creation, retrieval, and updates.

2. **HotelService**: Handles hotel-related functionalities, including hotel information and management.

3. **RatingService**: Manages user ratings for hotels, providing functionality to submit and retrieve ratings.

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
