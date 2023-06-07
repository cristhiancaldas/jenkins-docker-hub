@Library('jenkins-shared-library@main') _
pipeline {

  agent any

  tools{
        maven 'maven3'
    }

  environment {
     docker_repo = "crist"
     ImageName = 'jenkins-docker-hub'
     AppName = "message"
     ImageTag="v1"
  }

    stages {

     stage('🚀 Git-Checkout') {
           steps {
                gitCheckout(
                    branch: "main",
                    url: "https://github.com/cristhiancaldas/jenkins-docker-hub.git"
                )
            }
        }

     stage('🚀 Build-Maven'){
           steps {
            dir("${WORKSPACE}") {
                    sh 'mvn clean package'
          }
        }
     }

     stage(" 🚀 DockerBuild and Push") {
         steps {
             dir("${WORKSPACE}") {
                 dockerBuild ( "${ImageName}", "${docker_repo}" )
             }
         }
     }

     stage("🚀 Docker-CleanUP") {
        steps {
             dockerCleanup ( "${ImageName}", "${docker_repo}" )
        }
     }

     stage(" 🚀 Create deployment") {
         steps {
             sh 'echo ${WORKSPACE}'
             sh 'kubectl create -f ${WORKSPACE}/message/deployment-app.yml'
         }
     }

    }
}