FROM openjdk:8-jdk-alpine
EXPOSE 8092
WORKDIR /app
COPY ./target/SearchService.jar ./
CMD ["java", "-jar", "SearchService.jar"]

