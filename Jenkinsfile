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

    stage('🚀 Build Maven') {
      steps {
        git credentialsId: 'GitHub',branch: 'main' , url: 'https://github.com/cristhiancaldas/jenkins-docker-hub'
        //checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/cristhiancaldas/jenkins-docker-hub']]])
        sh 'mvn clean install'
      }
    }

    stage('🚀 Build Image...'){
         steps{
            script {
                   dockerImage = docker.build registry + ":$BUILD_NUMBER"
                   }
         }
    }

    stage('🚀 Login DockerHub'){
         steps{
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
             }
         }

    stage('🚀 Push DockerHub') {
          steps {
            script{
                // dockerImage.push()
                sh ' hola'
            }
             //sh "docker rmi $registry:$BUILD_NUMBER"
          }
        }

    stage('🚀 Deployment K8S'){
          steps{
             script{
                 kubernetesDeploy(configs: "deployment-app.yml")
                 //kubernetesDeploy (configs: 'deployment-app.yml',kubeconfigId: 'k8sconfigpwd')
             }
          }
    }

    }
    post {
      always {
        sh 'docker logout'
      }
    }
}