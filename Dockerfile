# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/spring-boot-project-1.0-SNAPSHOT.jar /app/app.jar

# Expose port 8080 (the port your Spring Boot app is configured to listen on)
EXPOSE 8080

# Define the command to run your application when the container starts
CMD ["java", "-jar", "app.jar"]
