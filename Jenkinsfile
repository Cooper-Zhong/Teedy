pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Static Code Analysis') {
            steps {
                script {
                    for (folder in ['docs-web', 'docs-core', 'docs-web-common']) {
                        dir(folder) {
                            sh 'mvn pmd:pmd'
                        }
                    }
                }
            }
            post {
                always {
                    archiveArtifacts artifacts: '**/docs-*/target/pmd.xml', fingerprint: true
                }
            }
        }
        stage('Unit Test') {
            steps {
                sh 'mvn test --fail-never'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/surefire-reports', fingerprint: true
                }
            }
        }
        stage('Generate Javadoc') {
            steps {
                script {
                    for (folder in ['docs-web', 'docs-core', 'docs-web-common']) {
                        dir(folder) {
                            sh 'mvn javadoc:jar'
                        }
                    }
                }
            }
            post {
                always {
                    archiveArtifacts artifacts: '**/docs-*/target/site/apidocs/**', fingerprint: true
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/site/**', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.war', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
        }
    }
}
