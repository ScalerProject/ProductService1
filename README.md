# ProductService Microservice

## Overview
ProductService is a Java Spring Boot microservice that manages products and categories, providing RESTful APIs for product operations. It is designed to be part of a scalable backend system, with integration to external APIs (like Fake Store API) and support for caching and persistence.

## Features
- CRUD operations for products
- Category management
- Pagination support for product listing
- Integration with external product APIs (Fake Store API)
- Redis caching for product data
- Configurable database connection (MySQL)
- Service discovery with Eureka

## Code Structure
```
src/main/java/com/scaler/productservicedec24/
├── controllers/         # REST API controllers
├── services/            # Business logic and API integration
├── models/              # JPA entities for Product and Category
├── repositories/        # Spring Data JPA repositories
├── dtos/                # Data Transfer Objects
├── exceptions/          # Custom exception classes
├── configs/             # Configuration classes
├── controlleradvices/   # Global exception handling
└── ProductServiceDec24Application.java # Main entry point
```

## API Endpoints
All endpoints are prefixed with `/products`.

| Method | Endpoint           | Description                        |
|--------|--------------------|------------------------------------|
| GET    | `/products/{id}`   | Get a single product by ID         |
| GET    | `/products`        | List all products (paginated)      |
| POST   | `/products`        | Create a new product               |
| DELETE | `/products/{id}`   | Delete a product by ID             |
| PATCH  | `/products/{id}`   | Update fields of a product         |
| PUT    | `/products/{id}`   | Replace a product                  |

### Example: Get a Product
```
GET /products/1
Response: 200 OK
{
  "id": 1,
  "title": "Product Title",
  "description": "Product Description",
  "price": 99.99,
  "imageUrl": "http://...",
  "category": { "value": "electronics" }
}
```

## Data Models
### Product
- `id`: Long
- `title`: String
- `description`: String
- `price`: Double
- `imageUrl`: String
- `category`: Category

### Category
- `id`: Long
- `value`: String (unique)

## Configuration
Configuration is managed via `src/main/resources/application.properties` and environment variables:
- `PRODUCT_SERVICE_DB_URL`: JDBC URL for MySQL
- `PRODUCT_SERVICE_USER`: DB username
- `PRODUCT_SERVICE_PASS`: DB password
- `PORT_NUMBER`: Server port
- Eureka service discovery settings

## Running Locally
1. **Clone the repository**
2. **Set environment variables** for DB connection and port
3. **Run MySQL** and ensure the database is accessible
4. **Start the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
5. The service will be available at `http://localhost:<PORT_NUMBER>`

## Testing
- Unit and integration tests are located in `src/test/java/com/scaler/productservicedec24/`
- To run tests:
  ```bash
  ./mvnw test
  ```

## Deployment
- Ensure all environment variables are set in your production environment
- Build the JAR:
  ```bash
  ./mvnw clean package
  ```
- Run the JAR:
  ```bash
  java -jar target/productservice-*.jar
  ```

## Notes
- The service uses Redis for caching product data.
- By default, product data is fetched from the Fake Store API if not found in cache.
- Service discovery is enabled via Eureka.

---
For further details, refer to the code and inline documentation in each class. 