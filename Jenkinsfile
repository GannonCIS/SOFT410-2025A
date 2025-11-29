pipeline {
    agent any

    stages {
        stage('Clean Workspace') {
            steps {
                echo 'Cleaning workspace.'
                deleteDir()
            }
        }

        stage('Build') {
            steps {
                script {
                    if (fileExists('./gradlew')) {
                        echo 'Using Gradle Wrapper.'
                        sh 'chmod +x ./gradlew'
                        sh './gradlew build'
                    } else {
                        echo 'Using system Gradle.'
                        sh 'gradle build'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    if (fileExists('./gradlew')) {
                        sh './gradlew test'
                    } else {
                        sh 'gradle test'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying.'
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
