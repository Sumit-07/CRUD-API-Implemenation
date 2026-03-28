# CRUD API Implementation

A Spring Boot CRUD (Create, Read, Update, Delete) API local server implementation using MySQL database.

## Overview

This project demonstrates a complete CRUD API implementation with Spring Boot and MySQL. It provides REST endpoints for managing friends and marksheet data with a clean, maintainable architecture.

## Project Structure

```
src/main/java/com/example/demo/
├── DemoApplication.java           # Spring Boot main application class
├── friends.java                   # Friends entity model
├── Marksheet.java                 # Marksheet entity model
├── FriendsRepository.java         # Data access layer for friends
├── FriendsResources.java          # REST controller for API endpoints
├── InputParametersException.java  # Custom exception class
├── Validator.java                 # Validation utilities
```

## Technologies Used

- **Java 8** - Programming language
- **Spring Boot 2.2.6.RELEASE** - Framework for building REST APIs
- **MySQL** - Database
- **Maven** - Build and dependency management
- **Lombok** - Reduce boilerplate code (getters/setters)

## Key Features

- RESTful API endpoints for CRUD operations
- MySQL database integration
- Input validation and error handling
- Custom exception handling
- Clean code with Lombok annotations for reduced boilerplate

## Project Setup

### Prerequisites

- Java 8 or higher
- MySQL Server
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Sumit-07/CRUD-API-Implemenation.git
   cd CRUD-API-Implemenation
   ```

2. **Configure Database**
   - Update database credentials in `application.properties` (if exists)
   - Create a MySQL database for the application

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The API will be available at `http://localhost:8080`

## API Endpoints

### Friends Management

- `GET /friends` - Retrieve all friends
- `GET /friends/{id}` - Retrieve a specific friend
- `POST /friends` - Create a new friend
- `PUT /friends/{id}` - Update a friend
- `DELETE /friends/{id}` - Delete a friend

### Marksheet Management

- `GET /marksheet` - Retrieve all marksheets
- `GET /marksheet/{id}` - Retrieve a specific marksheet
- `POST /marksheet` - Create a new marksheet
- `PUT /marksheet/{id}` - Update a marksheet
- `DELETE /marksheet/{id}` - Delete a marksheet

## Request/Response Examples

### Create Friend

**Request:**
```json
POST /friends
{
  "name": "John Doe",
  "city": "New York",
  "birthday": "1995-05-15",
  "classId": "A1"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "John Doe",
  "city": "New York",
  "birthday": "1995-05-15",
  "classId": "A1"
}
```

## Dependencies

All dependencies are managed through Maven. Key dependencies include:

- `spring-boot-starter-web` - For building REST APIs
- `mysql-connector-java` - For MySQL database connection
- `lombok` - For reducing boilerplate code

See `pom.xml` for complete dependency list.

## Code Quality Improvements

Recent refactoring has introduced:

- **Lombok Annotations** - Replaced manual getter/setter methods with `@Getter` and `@Setter` annotations
- **Reduced Boilerplate** - Significantly reduced code size while maintaining full functionality
- **Improved Maintainability** - Cleaner, more readable code

## Error Handling

The application includes custom exception handling:

- `InputParametersException` - Thrown when invalid parameters are provided
- Centralized error response format for all API endpoints

## Validation

Input validation is performed on all CRUD operations using the `Validator` utility class to ensure data integrity.

## Future Enhancements

- Add authentication and authorization
- Implement pagination for list endpoints
- Add comprehensive unit and integration tests
- Add API documentation with Swagger/OpenAPI
- Database migration scripts with Flyway/Liquibase

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add YourFeature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

## License

This project is open source and available under the MIT License.

## Author

**Sumit-07**

## Contact

For any questions or suggestions, please open an issue on the GitHub repository.

---

**Last Updated:** March 28, 2026