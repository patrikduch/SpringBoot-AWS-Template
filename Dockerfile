FROM openjdk:11
COPY ./springboot-aws-api/target/springboot-aws-api-deployed.jar .
EXPOSE 80
ENTRYPOINT ["java", "-jar", "springboot-aws-api-deployed.jar"]