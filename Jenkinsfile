pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-17'
        }
    }

    stages {
        stage('Run Automation Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}