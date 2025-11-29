pipeline {
    agent any

    stages {
        stage('Clean Workspace') {
            steps {
                echo 'Cleaning workspace...'
                cleanWs()
            }
        }

        stage('Build') {
            steps {
                echo 'Building...'
                sh './gradlew build'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing...'
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
            junit '**/build/test-results/test/*.xml'
            publishHTML(target: [
                reportDir: 'build/reports/tests/test',
                reportFiles: 'index.html',
                reportName: 'Test report',
                alwaysLinkToLastBuild: true,
                allowMissing: true,
                keepAll: true
            ])
        }
    }
}
