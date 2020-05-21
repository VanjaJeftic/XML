FROM openjdk:8-jdk-alpine
EXPOSE 8761
WORKDIR /app
COPY ./target/EurekaServer.jar ./
CMD ["java", "-jar", "EurekaServer.jar"]
