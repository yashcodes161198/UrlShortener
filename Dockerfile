# Use an appropriate base image with Java and Maven/Gradle installed
FROM amazoncorretto:17

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY /target/URLShortener-0.0.1-SNAPSHOT.jar /app/

# Expose the port your application runs on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "URLShortener-0.0.1-SNAPSHOT.jar"]
