# Backend - Spring Boot 3

## Pre-requisites

Ensure you have the following software installed on your system before setting up the Backend - Spring Boot 3 project:

1. **JDK (Java Development Kit):** Java Development Kit is required for compiling and running Java applications.
2. **Maven:** Maven is necessary for managing project dependencies and building the project.
3. **Git:** Git is essential for version control. Install Git to clone the project repository and manage source code changes.
4. **IDE (Integrated Development Environment):** Choose an IDE such as IntelliJ IDEA or Eclipse for convenient development and debugging.
5. **Docker:** Docker is required to run MySQL server and Zipkin. Additionally, Docker can be used for containerizing microservices for deployment.
6. **MySQL Workbench:** Although optional, MySQL Workbench is recommended for efficient management of MySQL databases.

## Project Setup

Follow these steps to set up and run the Backend - Spring Boot 3 project:

1. **Clone the Repository:**
```bash
   git clone <repository_url>
```

2. **Open the Project in IDE:**

Open the cloned repository in your preferred IDE.

3. **Review the Project Structure:**

Familiarize yourself with the project structure. Each microservice has its own directory/module within the repository.

4. **Build the Microservices:**

Navigate to each microservice directory/module and build it using Maven. For example:
```bash
   cd product-service
   mvn clean install
```

Repeat this step for each microservice.

5. **Set up Database and Zipkin tool:**

Navigate to the `config-server/src` directory and run the following command to set up MySQL server and Zipkin using Docker:

```bash
  cd config-server/src
  docker-compose up
```
6. **Run the Microservices:**

Start each microservice individually. You can either run them directly from the IDE or use Maven commands:
```bash
  cd product-service
  mvn spring-boot:run
```

7. **Test the Endpoints:**

   Once the microservices are up and running, test the endpoints using Swagger. Access Swagger UI through the following URL:

   http://localhost:[port]/swagger-ui/index.html


## Project Description

The Backend - Spring Boot 3 project aims to provide a comprehensive understanding of microservices architecture using Spring Boot. It demonstrates the integration of various technologies such as Eureka Server, Config Server, Zipkin, Swagger OpenAPI, Resilience4j, and more.

The project comprises multiple microservices, each representing a distinct functional component of an e-commerce web application. By adopting microservices architecture, the project emphasizes modularity, scalability, and resilience.

## Key Technologies

The project leverages the following key technologies:

1. **Eureka Server and Client:** Used for service discovery and registration within the microservices ecosystem.
2. **Config Server:** Centralized configuration management to maintain application properties and configurations.
3. **Spring Cloud Gateway:** Provides a dynamic routing and filtering mechanism for routing requests to microservices.
4. **Zipkin:** Distributed tracing system used for monitoring and debugging microservices-based applications.
5. **Swagger OpenAPI:** Generates interactive API documentation, making it easier to understand and consume APIs.
6. **Resilience4j:** Fault tolerance library for Java applications, providing features like circuit breakers and rate limiters.
7. **SpringDoc Swagger:** Enhanced support for OpenAPI documentation generation in Spring Boot applications.
8. **MySQL server:** Relational database management system used for storing application data.
9. **Docker Container:** Enables containerization of microservices for easy deployment and scaling.

---

## Snapshots

## Eureka Service Registry
![eureka](/Images/Eureka_Service_Registry.png)

## Zipkin
![Zipkin](/Images/Zipkin.png)

## Product Microservice
![Prodcut](/Images/Product.png)

## Shopping Cart Microservice
![ShoppingCart](/Images/ShoppingCart.png)

## Order Microservice
![Order](/Images/Order.png)