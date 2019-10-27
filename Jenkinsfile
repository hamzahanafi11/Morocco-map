pipeline {
  agent {
    node {
      label 'master'
    }

  }
  stages {
    stage('build app') {
      steps {
        sh 'javac src/CarteMaroc.java'
      }
    }
    stage('save artifact') {
      steps {
        archiveArtifacts 'src/CarteMaroc.class'
      }
    }
  }
}