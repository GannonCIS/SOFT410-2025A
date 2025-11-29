pipeline {
    agent any

    environment {
        POSTGRES_CONTAINER = "test-postgres"
        POSTGRES_DB = "auth_system"
        POSTGRES_USER = "postgres"
        POSTGRES_PASSWORD = "2331"
    }

    stages {
        stage('Start DB') {
            steps {
                echo 'Starting PostgreSQL container...'
                sh """
                docker run --name $POSTGRES_CONTAINER -e POSTGRES_USER=$POSTGRES_USER -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD -e POSTGRES_DB=$POSTGRES_DB -p 5432:5432 -d postgres
                sleep 10
                """
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'
                sh './gradlew build'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing..'
                sh './gradlew test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }

    post {
        always {
            echo 'Creating test report'
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
            sh "docker stop $POSTGRES_CONTAINER && docker rm $POSTGRES_CONTAINER || true"
        }
    }
}
