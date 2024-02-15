FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8090
COPY target/*.jar EmailService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "EmailService-0.0.1-SNAPSHOT.jar"]
