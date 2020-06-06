FROM openjdk:8-jdk-alpine
EXPOSE 8088
WORKDIR /app
COPY ./target/AgentAppBackend.jar ./
CMD ["java", "-jar", "AgentAppBackend.jar"]
