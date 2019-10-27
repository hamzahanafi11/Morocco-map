pipeline {
  agent {
    node {
      label 'mynode'
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