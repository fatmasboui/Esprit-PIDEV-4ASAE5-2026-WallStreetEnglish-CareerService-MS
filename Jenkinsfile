pipeline {
    agent any

    tools {
        maven 'maven3'
        // On essaie de déclarer docker comme outil au cas où il est configuré dans Jenkins
        // docker 'docker' 
    }

    environment {
        DOCKER_HUB_USER = 'fatmasboui'
        SERVICE_NAME = 'career-service'
        // Utilisation du bon ID trouvé dans assessment-service
        SONAR_TOKEN = credentials('sonar-cloud-token')
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
                    sh "mvn sonar:sonar -Dsonar.token=${SONAR_TOKEN} -Dsonar.projectKey=fatmasboui_Esprit-PiDev-4SAE5-2026-WallStreetEnglish-CareerService-MS -Dsonar.organization=fatmasboui -Dsonar.host.url=https://sonarcloud.io -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    // On essaie de construire l'image. 
                    // Si 'docker' n'est pas trouvé, il faudra peut-être installer le plugin Docker sur Jenkins
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
