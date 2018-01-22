pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''cd ./jenkins-spring-boot-client
mvn clean install'''
      }
    }
  }
}