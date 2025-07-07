# Dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/pilot-project-rest-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [java, -jar, app.jar]

