FROM openjdk:8-jdk-alpine
EXPOSE 8662
WORKDIR /app
COPY ./target/GatewayService.jar ./
CMD ["java", "-jar", "GatewayService.jar"]
