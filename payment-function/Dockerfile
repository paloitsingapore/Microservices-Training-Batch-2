FROM openjdk:alpine
WORKDIR /usr/java
EXPOSE 8080
COPY target/*.jar /usr/java/app.jar
ENTRYPOINT ["java", "-Dserver.port=8080", "-jar", "/usr/java/app.jar"]

