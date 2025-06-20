FROM openjdk:17-jdk-slim

WORKDIR /my-trip

COPY target/my-trip-0.0.1-SNAPSHOT.jar my-trip.jar

ENTRYPOINT ["java", "-jar", "my-trip.jar"]