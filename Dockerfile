# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/PaymentService-0.0.1-SNAPSHOT.jar payment-service.jar

# Expose the port your application listens on
EXPOSE 8080

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "payment-service.jar"]
