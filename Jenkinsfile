pipeline {
    agent any
    
    environment {
    	// FIREFOX_BINARY_PATH = '/snap/bin/firefox'
    	// GECKODRIVER_PATH = '/usr/local/bin/geckodriver'
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
            	// script {
                    // Update the PATH with both GECKODRIVER_PATH and FIREFOX_BINARY_PATH
                    // env.PATH = "${env.GECKODRIVER_PATH}:${env.FIREFOX_BINARY_PATH}:${env.PATH}"
                // }
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
                sh 'chmod +x ./scriptCleanSH.sh'
                sh './scriptCleanSH.sh'
                ansiblePlaybook colorized: true, disableHostKeyChecking: true, installation: 'Ansible', inventory: './inventory', playbook: 'playbook.yml'
            }
        }
    }
}
