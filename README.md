This is a small project using Spring Boot, Gradle, Swagger, Microservices and Docker.<br />
To start, <br />
1. User need to intall Docker software.<br />
2. Run the following command to start dockerized mysql:<br />
  docker run -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=catalytics -p 3306:3306 --name mysql_lin mysql:latest<br />
3. Run DockMan-registration<br />
4. Run DockMan-service<br />
5. Run DockMan-consumer<br />
<br />
More functionalities and css style will be added shortly:)
