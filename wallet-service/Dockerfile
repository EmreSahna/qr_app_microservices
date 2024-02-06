FROM amazoncorretto:17-alpine
RUN mkdir wallet-service
COPY target/*.jar wallet-service/wallet-service.jar
WORKDIR wallet-service
ENTRYPOINT ["java", "-jar", "wallet-service.jar"]
