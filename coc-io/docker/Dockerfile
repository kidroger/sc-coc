FROM openjdk:8-jre-alpine
COPY coc-io*.jar app.jar
EXPOSE 18500
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]