# Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests package || true
COPY src ./src
RUN mvn -q -DskipTests package

# Add the agent


# Run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/user-service-0.0.1-SNAPSHOT.jar user-service-0.0.1-SNAPSHOT.jar
COPY ./opentelemetry-javaagent.jar /otel/opentelemetry-javaagent.jar
EXPOSE 8080
ENTRYPOINT ["java", "-javaagent:/otel/opentelemetry-javaagent.jar", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]