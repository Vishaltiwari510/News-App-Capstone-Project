FROM openjdk:11.0.15-jre-slim
WORKDIR usr/src
ADD ./target/favouriteService.jar /usr/src/favouriteService.jar
ENTRYPOINT ["java","-jar","favouriteService.jar"]