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
                sh 'mvn pmd:pmd'
            }
            post {
                always {
                    archiveArtifacts artifacts: '**/docs-*/target/pmd.xml', fingerprint: true
                }
            }
        }
        // stage('Unit Test') {
        //     steps {
        //         sh 'mvn test --fail-never'
        //     }
        //     post {
        //         always {
        //             script {
        //                 junit '**/target/surefire-reports/TEST-*.xml'
        //             }
        //         }
        //     }
        // }

        stage('Generate Javadoc') {
            steps {
                sh 'mvn javadoc:jar'
                
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
