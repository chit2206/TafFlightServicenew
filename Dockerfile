# Use an official OpenJDK image as a base image
FROM amazoncorretto:17

# Set the working directory in the container
WORKDIR /app

COPY build/libs/filghtbook-ms.jar app.jar

# Expose the port the app will run on
EXPOSE 8087

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]