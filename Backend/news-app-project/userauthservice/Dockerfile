FROM openjdk:11.0.15-jre-slim
WORKDIR usr/src
ADD ./target/userauthservice.jar /usr/src/userauthservice.jar
ENTRYPOINT ["java","-jar","userauthservice.jar"]