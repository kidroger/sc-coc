FROM openjdk:8-jre-alpine
ADD coc-discovery*.jar app.jar
EXPOSE 18800
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]