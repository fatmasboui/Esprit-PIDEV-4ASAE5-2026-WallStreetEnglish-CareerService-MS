pipeline {
    agent any

    tools {
        maven 'maven3' 
    }

    environment {
        DOCKER_HUB_USER = 'fatmasboui'
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
                script {
                    try {
                        withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                            withSonarQubeEnv('sonarcloud') {
                                sh "mvn sonar:sonar -Dsonar.projectKey=fatmasboui_Esprit-PiDev-4SAE5-2026-WallStreetEnglish-CareerService-MS -Dsonar.organization=fatmasboui -Dsonar.host.url=https://sonarcloud.io -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml -Dsonar.login=${SONAR_TOKEN}"
                            }
                        }
                    } catch (Exception e) {
                        echo "SonarQube analysis failed, but continuing... error: ${e.message}"
                    }
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_HUB_USER}/${SERVICE_NAME}:latest ."
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
