pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
      }
    tools {
        maven 'MAVEN_HOME'
        jdk   'JAVA_HOME'
      }

    stages {
       stage('Build Maven'){
         steps{
             checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Java-Techie-jt/devops-automation']]])
             sh 'mvn clean install'
         }
       }

       stage('Build Image...'){
         steps{
             sh 'docker build -t crist/jenkins-docker-hub:${env.BUILD_ID} .'
         }
       }

       stage('Login and Push Docker Image...'){
         steps{
               script{
                     withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                     sh 'docker login -u crist -p ${dockerhubpwd}'
               }
                     sh 'docker push crist/jenkins-docker-hub:${env.BUILD_ID}'
             }
         }
       }

    }
}