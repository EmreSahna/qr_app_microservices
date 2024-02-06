FROM amazoncorretto:17-alpine
RUN mkdir transaction-service
COPY target/*.jar transaction-service/transaction-service.jar
WORKDIR transaction-service
ENTRYPOINT ["java", "-jar", "transaction-service.jar"]
