pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Automation Tests') {
            agent {
                docker {
                    image 'maven:3.9.9-eclipse-temurin-17'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'Generating Allure Report'
            allure([
                includeProperties: false,
                jdk: '',
                results: [[path: 'target/allure-results']]
            ])
        }
    }
}
