# Use the official OpenJDK 11 image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
VOLUME /tmp

# Copy the JAR file to the working directory
COPY target/ExamPortal-0.0.1-SNAPSHOT.jar ExamPortal.jar

# Command to run the Spring Boot application
ENTRYPOINT ["java","-jar","/ExamPortal.jar"]
EXPOSE 8080
