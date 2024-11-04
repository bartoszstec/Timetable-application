FROM openjdk:17-slim

RUN apt-get update && apt-get install -y findutils

# Set the working directory inside the container
WORKDIR /backend

# Copy the entire project into the container
COPY . .

# Build the application inside the container
RUN ./gradlew clean bootJar

# Copy the built JAR file to a known location
COPY build/libs/*.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]