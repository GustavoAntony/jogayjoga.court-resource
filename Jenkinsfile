pipeline {
    agent any
    stages {

        stage('Build Interface') {
            steps {
                build job: 'jogayjoga-court', wait: true
            }
        }

        stage('Build') { 
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Image') {
            steps {
                script {
                    court = docker.build("eriksoaress/court:${env.BUILD_ID}", "-f Dockerfile .")
                }
            }
        }

        stage('Push Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credential') {
                        court.push("${env.BUILD_ID}")
                        court.push("latest")
                    }
                }
            }
        }
    }
}