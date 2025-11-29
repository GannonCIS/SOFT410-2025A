pipeline {
    agent any

    environment {
        POSTGRES_PASSWORD = '2331'
        POSTGRES_DB = 'auth_system'
        POSTGRES_USER = 'postgres'
        POSTGRES_PORT = '5432'
    }

    stages {
        stage('Start PostgreSQL') {
            steps {
                echo 'Starting PostgreSQL container...'
                sh """
                    docker run -d --name pg-test \
                    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
                    -e POSTGRES_DB=${POSTGRES_DB} \
                    -e POSTGRES_USER=${POSTGRES_USER} \
                    -p ${POSTGRES_PORT}:${POSTGRES_PORT} \
                    postgres
                """
                echo 'Waiting for DB to be ready...'
                sh 'sleep 10'
            }
        }

        stage('Build') {
            steps {
                echo 'Building project...'
                sh './gradlew build'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
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
            echo 'Collecting test reports...'
            junit '**/build/test-results/test/*.xml'
            publishHTML(target: [
                reportDir: 'build/reports/tests/test',
                reportFiles: 'index.html',
                reportName: 'Test report',
                alwaysLinkToLastBuild: true,
                allowMissing: true,
                keepAll: true
            ])
            echo 'Stopping PostgreSQL container...'
            sh 'docker stop pg-test && docker rm pg-test'
        }
    }
}
