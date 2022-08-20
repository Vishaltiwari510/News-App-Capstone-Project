FROM openjdk:11.0.15-jre-slim
WORKDIR usr/src
ADD ./target/Eureka_server.jar /usr/src/Eureka_server.jar
ENTRYPOINT ["java","-jar","Eureka_server.jar"]