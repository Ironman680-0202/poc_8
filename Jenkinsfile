pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        DOCKER_IMAGE = "sandeep680/poc_1"
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=poc1 -Dsonar.projectName=poc1'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t sandeep680/poc_1:latest .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    sh '''
                    docker login -u $USER -p $PASS
                    docker push sandeep680/poc_1:latest
                    '''
                }
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker rm -f poc_1 || true
                docker run -d -p 8081:8080 --name poc_1 sandeep680/poc_1:latest
                '''
            }
        }
    }
}