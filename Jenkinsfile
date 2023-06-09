pipeline {
    agent any
    
    environment {
        registry = 'adrijsharma/classroom_management_iiitb'
        registryCredential = 'dockerhubconnect'
        dockerImage = ''
        LC_ALL = 'en_IN.UTF-8'
        LANG = 'en_IN.UTF-8'
        LANGUAGE = 'en_IN.UTF-8'
    }
    
    stages {
        stage('Pull GitHub Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/adrijsharma0408/Classroom-Management-IIITB'
            }
        }

        stage('Ensure Test DB is Running') {
            steps {
                sh 'docker-compose -f docker-composePROD.yml down'
                sh 'docker-compose -f docker-composeTEST.yml down'
                sh 'docker-compose -f docker-composeTEST.yml up -d' 
                sh 'sleep 2'
            }
        }
        
        stage('Build Pulled Code using Maven') {
            steps {
                sh 'mvn clean install'
            }
        }
        
        stage('Creating Image using Docker') {
            steps {
                script {
                    dockerImage = docker.build registry + ":latest"
                }
            }
        }
        
        stage('Pushing the Image to Docker Repository') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }
        
        stage('Ensure Production DB is Running') {
            steps {
                sh 'docker-compose -f docker-composePROD.yml down'
                sh 'docker-compose -f docker-composeTEST.yml down'
                sh 'docker-compose -f docker-composePROD.yml up -d'
                sh 'sleep 2'
            }
        }
        
        stage('Run Ansible for Deployment') {
            steps {
                ansiblePlaybook colorized: true, disableHostKeyChecking: true, installation: 'Ansible', inventory: './inventory', playbook: 'playbook.yml'
            }
        }
    }
}
