FROM openjdk:11-jdk-slim

LABEL AUHTOR="Cristhian Caldas Mendoza"

ADD target/*.jar devops-integration.jar

ENTRYPOINT ["java","-jar","/devops-integration.jar"]
