FROM openjdk:8-jdk-alpine
EXPOSE 8092
WORKDIR /app
COPY ./target/GatewayService.jar ./
CMD ["java", "-jar", "GatewayService.jar"]
