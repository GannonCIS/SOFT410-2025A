pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building with Gradle Wrapper.'
                sh 'chmod +x ./gradlew'
                sh './gradlew build'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests with Gradle Wrapper.'
                sh './gradlew test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying...'
            }
        }
    }

    post {
        always {
            echo 'Publishing test reports...'
            junit allowEmptyResults: true, testResults: '**/build/test-results/test/*.xml'
            publishHTML(target: [
                reportDir: 'build/reports/tests/test',
                reportFiles: 'index.html',
                reportName: 'Test Report',
                alwaysLinkToLastBuild: true,
                allowMissing: true,
                keepAll: true
            ])
        }
    }
}
