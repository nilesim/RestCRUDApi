FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tedtalkapi.jar
ENTRYPOINT ["java","-jar","/tedtalkapi.jar"]