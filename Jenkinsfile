pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'cd ./jenkins-spring-boot-client'
        sh 'mvn clean install'
      }
    }
  }
}