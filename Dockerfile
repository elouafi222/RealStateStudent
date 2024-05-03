# Étape de construction
FROM maven:3.8.2-jdk-11 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -Pprod -DskipTests

# Étape d'exécution
FROM openjdk:11-jdk-slim
LABEL authors="MohammedElouafi"
COPY --from=build /app/target/*.jar app.jar
ENV PORT=8080
EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "app.jar"]