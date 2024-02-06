FROM amazoncorretto:17-alpine
RUN mkdir notification-service
COPY target/*.jar notification-service/notification-service.jar
WORKDIR notification-service
ENTRYPOINT ["java", "-jar", "notification-service.jar"]
