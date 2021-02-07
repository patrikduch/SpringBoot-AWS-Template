FROM openjdk:12
VOLUME /tmp
ADD config/ /deploy/
COPY ./tsdb-api/target/tsdb-api-deployed.jar /deploy/
ADD ./config/application.yml .

EXPOSE 80

ENTRYPOINT ["java", "-Dspring.profiles.active=production","-Djava.security.egd=file:/dev/./urandom --spring.config.location=classpath:file:/application.yml","-jar","/deploy/tsdb-api-deployed.jar"]
