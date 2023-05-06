pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
        jdk   'JAVA_HOME'
      }

    stages {
       stage('Build Maven'){
         steps{
             checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/cristhiancaldas/jenkins-docker-hub']]])
             sh 'mvn clean install'
         }
       }

       stage('Build Image...'){
         steps{
             sh 'docker build -t crist/jenkins-docker-hub:${env.BUILD_NUMBER} .'
         }
       }

       stage('Login and Push Docker Image...'){
         steps{
               script{
                     withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                     sh 'docker login -u crist -p ${dockerhubpwd}'
               }
                     sh 'docker push crist/jenkins-docker-hub:${env.BUILD_NUMBER}'
             }
         }
       }

    }
}