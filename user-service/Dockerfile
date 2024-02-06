FROM amazoncorretto:17-alpine
RUN mkdir user-service
COPY target/*.jar user-service/user-service.jar
WORKDIR user-service
ENTRYPOINT ["java", "-jar", "user-service.jar"]
