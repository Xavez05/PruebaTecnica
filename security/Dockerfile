FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar security-0.0.1-SNAPSHOT.jar

EXPOSE 8084

CMD ["java", "-jar", "security-0.0.1-SNAPSHOT.jar"]