FROM amazoncorretto:17-alpine
RUN mkdir api-gateway
COPY target/*.jar api-gateway/api-gateway.jar
WORKDIR api-gateway
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
