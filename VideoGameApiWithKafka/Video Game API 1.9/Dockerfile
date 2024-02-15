FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ENV MYSQL_PASSWORD: test
EXPOSE 8080
COPY target/*.jar openapi-spring-1.0.0.jar
ENTRYPOINT ["java", "-jar", "openapi-spring-1.0.0.jar"]
