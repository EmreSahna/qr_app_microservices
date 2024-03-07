FROM golang:1.20-alpine
RUN mkdir url-shortener-service
COPY app url-shortener-service/app
WORKDIR url-shortener-service
EXPOSE 3000
ENTRYPOINT ["./app"]