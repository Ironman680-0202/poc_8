pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        DOCKER_IMAGE = "lovidovi29/java-app"
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

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE:latest .'
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
                    docker push $DOCKER_IMAGE:latest
                    '''
                }
            }
        }

        stage('Run Container') {
            steps {
                sh 'docker run -d -p 8080:8080 $DOCKER_IMAGE:latest'
            }
        }
    }
}