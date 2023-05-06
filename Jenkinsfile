pipeline {
  agent any
  stages {
    stage('Build Maven') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/cristhiancaldas/jenkins-docker-hub']]])
        sh 'mvn clean install'
      }
    }

    stage('Build Image...') {
      agent any
      steps {
        sh 'docker build -t crist/jenkins-docker-hub .'
      }
    }

  }
  tools {
    maven 'MAVEN_HOME'
    jdk 'JAVA_HOME'
  }
  environment {
    imageName = 'crist/jenkins-docker-hub'
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
}