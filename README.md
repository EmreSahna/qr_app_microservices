# QuickPayr Application

## Purpose of project

---

QuickPayr is an application that aims to perform fast and easy payment transactions between sellers and customers using the QR code payment path. 

# Services

- [User Service](https://github.com/EmreSahna/spring_microservices_qr_app/tree/main/user-service/src/main/java/com/emresahna/userservice)
- [Wallet Service](https://github.com/EmreSahna/spring_microservices_qr_app/tree/main/wallet-service/src/main/java/com/emresahna/walletservice)
- [Transaction Service](https://github.com/EmreSahna/spring_microservices_qr_app/tree/main/transaction-service/src/main/java/com/emresahna/transactionservice)
- [Notification Service](https://github.com/EmreSahna/spring_microservices_qr_app/tree/main/notification-service/src/main/java/com/emresahna/notificationservice)
- [Product Service](https://github.com/EmreSahna/spring_microservices_qr_app/tree/main/product-service/src/main/java/com/emresahna/productservice)
- [Eureka Service](https://github.com/EmreSahna/spring_microservices_qr_app/tree/main/eureka-server/src/main/java/com/emresahna/eurekaserver)
- [API Gateway Service](https://github.com/EmreSahna/spring_microservices_qr_app/tree/main/api-gateway/src/main/java/com/emresahna/apigateway)

## Technologies
- Spring Boot
- Spring Data JPA
- Spring Validation
- Spring Cloud Gateway
- Spring Cloud Eureka
- MySQL
- Apache Kafka
- Docker
- Lombok

## Saga Pattern

![Saga Pattern](/assets/SAGA.png "Saga Pattern")    

## Roadmap

- [x] Github Actions build and pushes to Docker Hub.
- [x] Implementing SAGA choreography pattern.
- [x] Product's images stored in AWS S3.
- [ ] Unit tests.
- [ ] Change Product Service's database to MongoDB.
- [ ] Logging.
- [ ] Exception handling.
- [ ] Security.
- [ ] Validation.
- [ ] Integration with k8s (Kubernetes).
- [ ] Circuit Breaker implementation.

## Progress

| Service              | Docker Hub                                                      | Finished | 
|----------------------|-----------------------------------------------------------------|----------|
| Api Gateway          | [Link](https://hub.docker.com/r/emresahna/api-gateway)          | :x:      |
| Eureka Server        | [Link](https://hub.docker.com/r/emresahna/eureka-server)        | :x:      |
| Wallet Service       | [Link](https://hub.docker.com/r/emresahna/wallet-service)       | :x:      |
| Product Service      | [Link](https://hub.docker.com/r/emresahna/product-service)      | :x:      |
| User Service         | [Link](https://hub.docker.com/r/emresahna/user-service)         | :x:      |
| Transaction Service  | [Link](https://hub.docker.com/r/emresahna/transaction-service)  | :x:      |
| Notification Service | [Link](https://hub.docker.com/r/emresahna/notification-service) | :x:      |