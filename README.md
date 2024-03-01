# Mini Web Framework
This project aims to create a lightweight web framework inspired by JAX-RS/Spring, focusing on controller-based request handling, JSON response creation, and supporting dependency injection at the controller level.

## Features
Controller-Based Request Handling: Utilize controllers annotated with @Controller to process incoming HTTP requests and generate responses.
Dependency Injection: Leverage dependency injection at the controller level to initialize controller attributes, enhancing modularity and testability.
Route Registration: Define available routes in the application by annotating controller methods with @Path, specifying the path and the HTTP method (@GET or @POST) to handle requests.
Discovery Mechanism: Automatically discover classes annotated with @Controller at startup, mapping routes to controller methods based on @GET/@POST and @Path annotations.
Singleton Controllers: Ensure controllers are instantiated only once, maintaining a ready instance for each new request.
Getting Started
Follow these instructions to get the project up and running on your local machine for development and testing purposes.

## Prerequisites
Ensure you have a suitable development environment with Java and your preferred IDE installed (e.g., IntelliJ IDEA, Eclipse).

## Setting Up
1.Clone the Repository
2.Navigate to the Project Directory
3.Build the Project

Use your IDE's build functionality or run a build command if applicable.

## Running the Framework
Start the application from your IDE by running the main class that initializes the framework.
Ensure you've set up the project to automatically discover controller classes and initialize them with dependencies.
Using the Framework
Define Controllers: Annotate your controller classes with @Controller and define methods to handle requests using @GET/@POST and @Path annotations.

Implement Dependency Injection: Use @Autowired to mark attributes for injection. Utilize @Bean, @Service, @Component, and @Qualifier annotations to manage your dependencies and their lifecycles.

Configure Routes: Specify routes and HTTP methods using @Path, @GET, and @POST annotations on controller methods.

Run Your Application: With the framework and your application configured, start the application to listen for incoming requests and route them to the appropriate controllers.



## Running the Application
**1.** Starting the Server
Before using the framework or running any tests, you need to start the server. This is done by running the main class that initializes and starts the server component of the framework.

**2.** Navigate to the Server Main Class

**3.** Locate the main class for the server in your project. This class contains the main method responsible for starting the server.

**4.** Run the Server Main Class

**4.** Using your IDE, run the server main class. This action starts the server and makes it listen for incoming requests.

## Running Tests or Test Client
**1.** After the server is up and running, you can start the test main class or your test client to interact with the server.

**2.** Navigate to the Test Main Class or Test Client

**3.** Locate the main class or method designed for testing or acting as a test client. This could be a set of unit tests, integration tests, or a simple client that makes requests to your server.

**4.** Run the Test Main Class or Test Client
