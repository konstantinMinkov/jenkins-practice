pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'cd ./jenkins-spring-boot-sso'
        sh 'mvn clean install'
      }
    }
  }
}