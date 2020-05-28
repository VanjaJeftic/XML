FROM openjdk:8-jdk-alpine
EXPOSE 8098
WORKDIR /app
COPY ./target/MailService.jar ./
CMD ["java", "-jar", "MailService.jar"]
