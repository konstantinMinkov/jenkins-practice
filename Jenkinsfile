pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'cd ./jenkins-spring-boot-sso & mvn clean install'
      }
    }
  }
}