pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('sonar-token')
        DOCKER_HUB_USER = 'fatmasboui' // <-- Remplacez par votre pseudo Docker Hub si différent
        SERVICE_NAME = 'career-service'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Unit Tests') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarcloud') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=fatmasboui_Esprit-PiDev-4SAE5-2026-WallStreetEnglish-CareerService-MS -Dsonar.organization=fatmasboui -Dsonar.host.url=https://sonarcloud.io -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    // Construction de l'image
                    sh "docker build -t ${DOCKER_HUB_USER}/${SERVICE_NAME}:latest ."
                    
                    // Connexion et Push vers Docker Hub
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
                        sh "echo \$DOCKER_HUB_PASSWORD | docker login -u \$DOCKER_HUB_USERNAME --password-stdin"
                        sh "docker push ${DOCKER_HUB_USER}/${SERVICE_NAME}:latest"
                    }
                }
            }
        }
    }

    post {
        success {
            echo "CI/CD Pipeline finished successfully!"
        }
        failure {
            echo "Pipeline failed. Check the logs."
        }
    }
}
