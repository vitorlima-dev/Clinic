FROM openjdk:17-jdk
WORKDIR = /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} spring-app.jar
ENTRYPOINT ["java","-jar","spring-app.jar"]