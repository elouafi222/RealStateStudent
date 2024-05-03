# Étape de construction
FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk maven -y
WORKDIR /app
COPY . .
RUN mvn clean package

# Étape de packaging final
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/StudentHome1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]