pipeline {
  tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }
    agent any


   environment {
        registry = "crist/jenkins-docker-hub"
        dockerImage = ''
        DOCKERHUB_CREDENTIALS = credentials('docker-cred')
    }

  stages {

    stage('🚀 Build Maven') {
      steps {
        git credentialsId: 'github-cred',branch: 'main' , url: 'https://github.com/cristhiancaldas/jenkins-docker-hub'
        //checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/cristhiancaldas/jenkins-docker-hub']]])
        sh 'mvn install -DskipTests'
      }
    }

   stage('🚀 Build Docker Image') {
         steps {
                sh 'docker build -t $registry:$BUILD_NUMBER .'
         }
   }

   stage('🚀 Login DockerHub'){
         steps{
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
         }
   }

   stage('🚀 Push Image DockerHub') {
         steps{
                sh 'docker push $registry:$BUILD_NUMBER'
                sh 'docker rmi $registry:$BUILD_NUMBER'
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
