# Base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the build artifact (JAR file) from the host into the container
COPY build/libs/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]