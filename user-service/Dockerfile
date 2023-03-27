FROM maven:3.8.4-openjdk-17-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN mvn dependency:go-offline

COPY src src
RUN mvn package -DskipTests

FROM openjdk:17
WORKDIR user-service
COPY --from=build target/*.jar user-service.jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]