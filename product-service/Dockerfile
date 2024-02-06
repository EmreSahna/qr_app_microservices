FROM amazoncorretto:17-alpine
RUN mkdir product-service
COPY target/*.jar product-service/product-service.jar
WORKDIR product-service
ENTRYPOINT ["java", "-jar", "product-service.jar"]
