FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./target/docker-app.jar .

ENV SERVER_PORT = 8080

CMD ["java", "-jar", "docker-app.jar"]