# Stage 1: Build the Maven dependencies and package the application
FROM maven:3.8.8-eclipse-temurin-21 AS build
 
# Set the working directory in the container
WORKDIR /app
 
# Copy the Maven project files first to leverage caching of dependencies
COPY pom.xml .
 
# Download Maven dependencies (cache them)
RUN mvn dependency:go-offline
 
# Copy the source code into the container
COPY src ./src
 
# Package the application into a JAR file (skip tests for faster build)
RUN mvn clean package -DskipTests
 
# Stage 2: Prepare the runtime environment with a slim base image
FROM eclipse-temurin:21-jre-alpine AS runtime
 
# Set the working directory in the container
WORKDIR /app
 
# Copy only the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar
 
# Expose the application's port
EXPOSE 8080
 
# Set the entry point to execute the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
