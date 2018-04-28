FROM openjdk:8-jre-alpine
ADD coc-player*.jar app.jar
EXPOSE 18600
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]