pipeline {
    agent any
    
    tools {
        maven 'maven3'
    }

    environment {
        SONAR_TOKEN = credentials('sonar-cloud-token')
        // Remplacez par votre projet SonarCloud spécifique au Career Service
        SONAR_PROJECT_KEY = "fatmasboui_Esprit-PIDEV-4SAE5-2026-WallStreetEnglish-Career-MS" 
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                sh "mvn sonar:sonar -Dsonar.projectKey=${SONAR_PROJECT_KEY} -Dsonar.token=${SONAR_TOKEN}"
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t career-service:latest .'
            }
        }
    }

    post {
        success {
            echo "Pipeline Career Service terminé avec succès !"
        }
        failure {
            echo "Le pipeline a échoué."
        }
    }
}
