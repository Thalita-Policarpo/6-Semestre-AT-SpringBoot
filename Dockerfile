FROM open-jdk:17-jdk-slim

WORKDIR /app

COPY target/AssessementSpringBoot-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT["java","-jar","app.jat" ]
