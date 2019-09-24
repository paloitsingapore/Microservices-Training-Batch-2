FROM openjdk:8-alpine
WORKDIR /app/usr
COPY target/*.jar app.jar
EXPOSE 8080
RUN apk install curl
ENTRYPOINT ["java", "-Dserver.port=8080", "-jar", "app.jar"]