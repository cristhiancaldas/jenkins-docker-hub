pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
        jdk   'JAVA_HOME'
      }
    environment {
        imageName = "crist/jenkins-docker-hub"
        registryCredential = 'dockerhub'
        dockerImage = ''
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
               //dockerImage = docker.build("monishavasu/my-react-
             sh 'docker build -t crist/jenkins-docker-hub .'
         }
       }

    /*   stage('Login and Push Docker Image...'){
         steps{
               script{
                     withCredentials([string(credentialsId: 'dockerhub', variable: 'dockerhubpwd')]) {
                     sh 'docker login -u crist -p ${dockerhubpwd}'
               }
                     sh 'docker push crist/jenkins-docker-hub:${env.BUILD_NUMBER}'
             }
         }
       }
*/
    }
}