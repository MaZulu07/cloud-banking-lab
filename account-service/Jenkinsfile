pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo/account-service.git'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("account-service:latest")
                }
            }
        }
        stage('Run Tests') {
            steps {
                sh 'pytest ./app/tests'
            }
        }
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh 'docker login -u $USER -p $PASS'
                    sh 'docker tag account-service:latest yourdockerhubuser/account-service:latest'
                    sh 'docker push yourdockerhubuser/account-service:latest'
                }
            }
        }
    }
}
