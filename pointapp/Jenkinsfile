pipeline {
    agent any

    stages {
        stage('Check') {
            steps {
                git branch: 'develop',credentialsId:'github-workjo-org', url:'https://github.com/spharos-work-jo/backend'
            }
        }
        stage('Build'){
            steps{
                sh '''
                    cd pointapp
                    chmod +x ./gradlew
                    ./gradlew build -x test
                '''
            }
        }
        stage('DockerSize'){
            steps {
                sh '''
                    cd pointapp
                    docker stop ssgpoingapp || true
                    docker rm ssgpoingapp || true
                    docker rmi ssgpoint_be || true
                    docker build -t ssgpoint_be .
                '''
            }
        }
        stage('Deploy'){
            steps{
                sh 'docker run -d --name ssgpoingapp -p 8080:8000 ssgpoint_be'
            }
        }
    }
}
