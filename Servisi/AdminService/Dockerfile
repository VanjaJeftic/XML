FROM openjdk:8-jdk-alpine
EXPOSE 8094
WORKDIR /app
COPY ./target/AdminService.jar ./
CMD ["java", "-jar", "AdminService.jar"]
