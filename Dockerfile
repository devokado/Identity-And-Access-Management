FROM openjdk:11-jdk-slim
VOLUME /tmp


EXPOSE 8000
ADD target/accountManagement-0.0.1-SNAPSHOT.jar accountManagement-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/accountManagement-0.0.1-SNAPSHOT.jar"]