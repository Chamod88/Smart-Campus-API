# Smart Campus Sensor & Room Management API

## Overview
This project is a RESTful API developed using **Java JAX-RS (Jersey)** as part of the Client-Server Architectures coursework.

The system simulates a **Smart Campus** environment where rooms and sensors are managed, and sensor readings are recorded. The API supports:

- Room management
- Sensor registration and filtering
- Nested sensor readings
- Validation and error handling
- Logging of API requests and responses

The system uses **in-memory data structures (HashMap, ArrayList)** and does not use any database, as required.

---

## Technologies Used

- Java 17
- JAX-RS (Jersey)
- Apache Tomcat
- Maven

---

## How to Run the Project

1. Clone the repository:

git clone https://github.com/Chamod88/Smart-Campus-API.git

2.Open the project in IntelliJ IDEA
3.Build the project:
mvn clean package

4.Configure Apache Tomcat in IntelliJ

5.Deploy artifact:
smart-campus-api:war exploded

6.Run the server

7.Access API at:
http://localhost:8080/smart-campus-api/api/v1

API Endpoints

1.Discovery
GET /api/v1

2.Rooms

GET    /api/v1/rooms
POST   /api/v1/rooms
GET    /api/v1/rooms/{roomId}
DELETE /api/v1/rooms/{roomId}

3.Sensors
GET  /api/v1/sensors
GET  /api/v1/sensors?type=CO2
POST /api/v1/sensors

4.Sensor Readings
GET  /api/v1/sensors/{sensorId}/readings
POST /api/v1/sensors/{sensorId}/readings

5.curl Commands
curl http://localhost:8080/smart-campus-api/api/v1
curl http://localhost:8080/smart-campus-api/api/v1/rooms

curl -X POST http://localhost:8080/smart-campus-api/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{"id":"ENG-201","name":"Engineering Lab","capacity":50}'

curl -X POST http://localhost:8080/smart-campus-api/api/v1/sensors \
-H "Content-Type: application/json" \
-d '{"id":"CO2-001","type":"CO2","status":"ACTIVE","currentValue":0,"roomId":"ENG-201"}'

curl -X POST http://localhost:8080/smart-campus-api/api/v1/sensors/CO2-001/readings \
-H "Content-Type: application/json" \
-d '{"value":420.7}'

 Answers
1. JAX-RS Resource Lifecycle

By default, JAX-RS resource classes are typically instantiated per request. This means a new instance is created for each incoming HTTP request. This improves thread safety because instance variables are not shared across requests. However, it also means that in-memory data stored inside resource classes will not persist between requests. Therefore, shared data must be stored in static structures such as HashMap or managed in separate classes.

2. HATEOAS (Hypermedia)

Hypermedia is considered a key feature of REST because it allows clients to discover API functionality dynamically. Instead of relying only on external documentation, responses include links to related resources such as /rooms and /sensors. This reduces tight coupling between client and server and makes the API easier to evolve.

3. IDs vs Full Objects

Returning only IDs reduces response size and improves performance but requires additional requests from the client to fetch full details. Returning full objects provides more information in a single request but increases payload size. The choice depends on whether efficiency or convenience is prioritized.

4. DELETE Idempotency

DELETE is idempotent because performing the same delete request multiple times results in the same final state. After the first deletion, the resource no longer exists. Subsequent DELETE requests do not change the system state further, even though the response may differ.

5. @Consumes(MediaType.APPLICATION_JSON)

This annotation specifies that the endpoint only accepts JSON input. If a client sends data in another format, such as XML or plain text, the request will fail with an HTTP 415 error. This ensures consistent data processing.

6. Query Parameters vs Path Parameters

Query parameters are better for filtering because they allow optional criteria without changing the resource structure. For example, /sensors?type=CO2 filters results within the same collection, while path parameters are better suited for identifying specific resources.

7. Sub-Resource Locator Pattern

The sub-resource locator pattern allows nested resources to be handled by separate classes. This improves modularity and maintainability by separating concerns. Instead of having all logic in one large controller, each resource is responsible for its own behavior.

8. HTTP 422 vs 404

HTTP 422 is more appropriate when the request structure is valid, but the data inside it is invalid. In this case, the JSON is correct, but the referenced roomId does not exist. This differs from 404, which indicates a missing endpoint or resource.

9. Security Risks of Stack Traces

Exposing stack traces can reveal internal implementation details such as class names, file paths, and system structure. Attackers can use this information to exploit vulnerabilities. Therefore, APIs should return generic error messages instead of internal details.

10. Logging with Filters

Using JAX-RS filters for logging centralizes request and response tracking. This avoids duplicating logging logic in every resource method and ensures consistency across the entire API. It improves maintainability and simplifies debugging.

Conclusion

This project demonstrates a complete RESTful API implementation using JAX-RS. It includes proper resource design, validation, nested endpoints, exception handling, and logging, following industry best practices and coursework requirements.
