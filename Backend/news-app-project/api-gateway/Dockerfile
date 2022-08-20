FROM openjdk:11.0.15-jre-slim
WORKDIR usr/src
ADD ./target/api-gateway.jar /usr/src/api-gateway.jar
ENTRYPOINT ["java","-jar","api-gateway.jar"]