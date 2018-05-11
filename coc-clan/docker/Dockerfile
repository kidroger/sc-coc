FROM openjdk:8-jre-alpine
MAINTAINER jaracks <jaracks@outlook.com>
COPY coc-clan*.jar app.jar
EXPOSE 18700
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]