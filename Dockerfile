# Use the official OpenJDK 11 image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the working directory
COPY target/ExamPortal-0.0.1-SNAPSHOT.jar app.jar

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
