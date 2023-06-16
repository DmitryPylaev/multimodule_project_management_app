FROM openjdk:18.0.2.1-slim-buster
ARG JAR_FILE=*.jar
COPY ./school-app/target/${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]