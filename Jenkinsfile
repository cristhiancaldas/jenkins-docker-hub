pipeline {

  /*  agent {
      docker {
        args '--user root -v /var/run/docker.sock:/var/run/docker.sock' // mount Docker socket to access the host's Docker daemon
      }
    }
*/
    environment {
        registry = "crist/jenkins-docker-hub"
        dockerImage = ''
        DOCKERHUB_CREDENTIALS = credentials('docker-cred')
    }

  agent any

  stages {

    stage('🚀 Build Maven') {
      steps {
        git credentialsId: 'github-cred',branch: 'main' , url: 'https://github.com/cristhiancaldas/jenkins-docker-hub'
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
                dockerImage.push()
            }
             sh "docker rmi $registry:$BUILD_NUMBER"
          }
        }

   /* stage('🚀 Deployment K8S'){
          steps{
             script{
              withKubeConfig(caCertificate: '', clusterName: '', contextName: '', credentialsId: 'K8S', namespace: '', serverUrl: '') {
                             sh ('kubectl apply -f  deployment-app.yml')
             }
          }
    }
    }*/
}
    post {
      always {
        sh 'docker logout'
      }
    }
}