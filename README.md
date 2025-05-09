# BookStore Application

The **BookStore Application** is a Java-based web application that provides RESTful APIs for managing books, authors, customers, orders, and carts. It uses JAX-RS (Jersey) for building RESTful web services and supports JSON and XML data formats.

## Features

- **Books Management**: Create, retrieve, update, and delete books.
- **Authors Management**: Manage authors and their associated books.
- **Customers Management**: Handle customer data and operations.
- **Orders Management**: Place and retrieve customer orders.
- **Cart Management**: Add, update, and remove items in a customer's cart.
- **Exception Handling**: Custom exception handling for better error responses.


## Prerequisites

- **Java 8** or higher
- **Apache Maven** for build automation
- **GlassFish** or any other servlet container for deployment

## Dependencies

The project uses the following dependencies, as defined in the [pom.xml](pom.xml):

- **Jersey**: For building RESTful APIs
- **Jackson**: For JSON processing
- **JAXB**: For XML binding

## Configuration

### Persistence Configuration

The persistence configuration is defined in `persistence.xml`. Update the `persistence-unit` settings as needed.

### Web Configuration

The servlet and application configurations are defined in `web.xml`.

## Build and Run

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd BookStore

2. **Build the project**:
   ```bash
   mvn clean install

3. **Deploy the WAR file**: Deploy the generated BookStore.war file (found in the target/ directory) to your servlet container (e.g., GlassFish).
4. **Access the application**: Open your browser and navigate to http://<server>:<port>/BookStore.

## API Endpoints

### Base URL
http://<server>:<port>/BookStore/rest

### Resources

#### Books

- **GET** `/books`  
  *Retrieve a list of all books.*

- **GET** `/books/{id}`  
  *Retrieve details of a specific book by its ID.*

- **POST** `/books`  
  *Add a new book.*  
  **Request Body**: JSON or XML representation of the book.

- **PUT** `/books/{id}`  
  *Update an existing book by its ID.*  
  **Request Body**: JSON or XML representation of the updated book.

- **DELETE** `/books/{id}`  
  *Delete a book by its ID.*

#### Authors

- **GET** `/authors`  
  *Retrieve a list of all authors.*

- **GET** `/authors/{id}`  
  *Retrieve details of a specific author by their ID.*

- **POST** `/authors`  
  *Add a new author.*  
  **Request Body**: JSON or XML representation of the author.

- **PUT** `/authors/{id}`  
  *Update an existing author by their ID.*  
  **Request Body**: JSON or XML representation of the updated author.

- **DELETE** `/authors/{id}`  
  *Delete an author by their ID.*

#### Customers

- **GET** `/customers`  
  *Retrieve a list of all customers.*

- **GET** `/customers/{id}`  
  *Retrieve details of a specific customer by their ID.*

- **POST** `/customers`  
  *Add a new customer.*  
  **Request Body**: JSON or XML representation of the customer.

- **PUT** `/customers/{id}`  
  *Update an existing customer by their ID.*  
  **Request Body**: JSON or XML representation of the updated customer.

- **DELETE** `/customers/{id}`  
  *Delete a customer by their ID.*

#### Orders

- **GET** `/customers/{customerId}/orders`  
  *Retrieve all orders for a specific customer.*

- **POST** `/customers/{customerId}/orders`  
  *Place a new order for a customer.*  
  **Request Body**: JSON or XML representation of the order.

#### Cart

- **GET** `/customers/{customerId}/cart`  
  *Retrieve the cart for a specific customer.*

- **POST** `/customers/{customerId}/cart`  
  *Add an item to the customer's cart.*  
  **Request Body**: JSON or XML representation of the cart item.

- **PUT** `/customers/{customerId}/cart`  
  *Update the customer's cart.*  
  **Request Body**: JSON or XML representation of the updated cart.

- **DELETE** `/customers/{customerId}/cart/{itemId}`  
  *Remove an item from the customer's cart by its ID.*
