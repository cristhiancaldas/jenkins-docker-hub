def COLOR_MAP = [
    'SUCCESS': 'good',
    'FAILURE': 'danger',
]

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

     stage('🚀 Git-Checkout ') {
           steps {
                checkoutCall(
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

    stage('🚀 SonarQube Analysis') {
        steps{
         withSonarQubeEnv('sonar-server') {
            sh "mvn clean package sonar:sonar -Dsonar.projectKey=jenkins-sonar"
              }
          }
      }


    /* stage(" 🚀 Docker Build and Push") {
         steps {
             dir("${WORKSPACE}") {
                 dockerBuildCall ( "${ImageName}", "${docker_repo}" )
             }
         }
     }*/

    /* stage("🚀 Docker-CleanUP") {
        steps {
             dockerCleanupCall ( "${ImageName}", "${docker_repo}" )
        }
     }*/

    /* stage(" 🚀 Create deployment K8S") {
         steps {
             sh 'echo ${WORKSPACE}'
             sh 'kubectl apply -f ${WORKSPACE}/deployment-app.yml'
         }
     }*/

    /* stage("🚀 wait_for_pods"){
     	 steps{
             sh 'sleep 300'
         }
     }*/

     /*stage("🚀 rollback deployment") {
     	  steps {
     	      sh """
     	           kubectl delete deploy ${AppName}
     		       kubectl delete svc ${AppName}
     			  """
     	  }
     }*/

    }
    /* post {
            always {
                echo 'Slack Notifications.'
                slackSend channel: '#docker',
                    color: COLOR_MAP[currentBuild.currentResult],
                    message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} build ${env.BUILD_NUMBER} \n More info at: ${env.BUILD_URL}"
                }

            }*/
}

def checkoutCall(Map stageParams) {
    checkout([
        $class: 'GitSCM',
        branches: [[name:  stageParams.branch ]],
        userRemoteConfigs: [[ url: stageParams.url ]]
    ])
}


def dockerBuildCall(String project, String hubUser) {
    sh "docker image build -t ${hubUser}/${project} ."
    sh "docker tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}"
    sh "docker tag ${hubUser}/${project} ${hubUser}/${project}:latest"
    withCredentials([usernamePassword(
            credentialsId: "docker-hub",
            usernameVariable: "USER",
            passwordVariable: "PASS"
    )]) {
        sh "docker login -u '$USER' -p '$PASS'"
    }
    sh "docker image push ${hubUser}/${project}:${ImageTag}"
    sh "docker image push ${hubUser}/${project}:latest"
}

def dockerCleanupCall(String project, String hubUser) {
    sh "docker rmi ${hubUser}/${project}:${ImageTag}"
    sh "docker rmi ${hubUser}/${project}:latest"
}