# Use an appropriate base image with Java and Maven/Gradle installed
FROM adoptopenjdk:17-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/your-spring-boot-app.jar /app/

# Expose the port your application runs on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "your-spring-boot-app.jar"]
