FROM openjdk:11-jdk-slim
VOLUME /tmp


EXPOSE 8000
ADD target/Identity-And-Access-Management-0.0.1-SNAPSHOT.jar Identity-And-Access-Management-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/mesghal-0.0.1-SNAPSHOT.jar"]