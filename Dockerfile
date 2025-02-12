FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src /app/src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-alpine-3.21
WORKDIR /app
COPY --from=build /app/target/*.jar /app/users-manager-app.jar
CMD ["java", "-Duser.timezone=America/Argentina/Buenos_Aires", "-jar", "/app/users-manager-app.jar"]