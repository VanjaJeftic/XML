FROM openjdk:8-jdk-alpine
EXPOSE 8099
WORKDIR /app
COPY ./target/MessageService.jar ./
CMD ["java", "-jar", "MessageService.jar"]
