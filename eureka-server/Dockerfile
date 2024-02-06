FROM amazoncorretto:17-alpine
RUN mkdir eureka-server
COPY target/*.jar eureka-server/eureka-server.jar
WORKDIR eureka-server
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]
