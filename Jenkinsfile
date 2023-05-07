pipeline {
    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }
    environment {
        registry = "crist/jenkins-docker-hub"
        registryCredential = 'dockerhub'
        dockerImage = ''
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')

    }

  agent any

  stages {
    stage('Build Maven') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/cristhiancaldas/jenkins-docker-hub']]])
        sh 'mvn clean install'
      }
    }

    stage('Build Image...'){
         steps{
            script {
                   dockerImage = docker.build registry + ":$BUILD_NUMBER"
                   }
         }
    }

    stage('Login Docker '){
         steps{
           sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
               //script{
                   /*  docker.withRegistry( '', registryCredential ) {
                     dockerImage.push()
                     //dockerImage.push("latest")
                    }*/
               //}
                   //  sh 'docker push crist/jenkins-docker-hub:${env.BUILD_NUMBER}'
             }
         }
    stage('Push Docker Hub') {
          steps {
            sh 'docker push dockerImage'
          }
        }
  }
}