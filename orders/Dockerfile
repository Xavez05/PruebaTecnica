FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar orders-0.0.1-SNAPSHOT.jar

EXPOSE 8082

CMD ["java", "-jar", "orders-0.0.1-SNAPSHOT.jar"]