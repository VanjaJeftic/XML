FROM openjdk:8-jdk-alpine
EXPOSE 8092
WORKDIR /app
COPY ./target/AuthorizationService.jar ./
CMD ["java", "-jar", "AuthorizationService.jar"]
