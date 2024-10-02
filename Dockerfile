# Base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the build.gradle and settings.gradle files
COPY build.gradle settings.gradle ./

# Copy the Gradle wrapper
COPY gradlew ./
COPY gradle ./gradle

# Copy the source files
COPY src ./src

# Build the project
RUN ./gradlew build -x test

# Copy the jar file
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]