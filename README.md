This is a small project using Spring Boot, Gradle, Swagger, Microservices and Docker.
To start, 
1. User need to intall Docker software.
2. Run the following command to start dockerized mysql:
  docker run -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=catalytics -p 3306:3306 --name mysql_lin mysql:latest
3. Run DockMan-registration
4. Run DockMan-service
5. Run DockMan-consumer

More functionalities and css style will be added shortly:)
