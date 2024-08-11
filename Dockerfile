FROM eclipse-temurin:17-jre-alpine

# Copy the Gradle build files and the Spring Boot application jar file into the container
COPY build/libs/*.jar /app/application.jar

# Set the working directory to the application directory
WORKDIR /app

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "application.jar"]
