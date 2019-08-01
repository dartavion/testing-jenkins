pipeline {
    agent any

    tools { nodejs "lts" }

    stages {

        stage('Cloning Git') {
            steps {
                git 'https://github.com/dartavion/testing-jenkins.git'
            }
        }
        stage('Install dependencies') {
            steps {
                sh 'npm install'
            }
        }
        stage('Pick Which Test to Run') {
            steps {
                script {
                    git url: 'https://github.com/dartavion/testing-jenkins.git';
                    def getGitFileList = load('getGitFileList.groovy')
                    def fileList = getGitFileList(new File(pwd()))
                    def selectedFile = input(id: 'userInput', message: 'Choose properties file', parameters: [[$class: 'ChoiceParameterDefinition', choices: fileList, description: 'Properties', name: 'prop']])
                    println "Property: $selectedFile"
                    build job: 'usom-regression-tests', parameters: [[$class: 'StringParameterValue', name: 'prop', value: selectedFile]]
                }
            }
        }

        stage('run regression test') {
            steps {
                sh 'npm config ls'
            }
        }
    }
}