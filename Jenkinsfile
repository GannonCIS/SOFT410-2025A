pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                bat 'gradlew build'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing...'
                bat 'gradlew test -Dtest.env=true'
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
