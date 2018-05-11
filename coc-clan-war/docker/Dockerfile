FROM openjdk:8-jre-alpine
COPY coc-war*.jar app.jar
EXPOSE 18900
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]