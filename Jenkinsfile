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
                    final foundFiles = sh(script: 'ls -1 tests', returnStdout: true).split()
                    println "FOUND FILES: $foundFiles"
                    def getGitFileList = load('getGitFileList.groovy')
                    def fileList = getGitFileList.inputParamsString(foundFiles)
                    def selectedFile = input(id: 'userInput', message: 'Choose properties file', parameters: [[$class: 'ChoiceParameterDefinition', choices: fileList, description: 'Properties', name: 'prop']])
                    println "Property: $selectedFile"

//                    build job: 'regression-pipeline', parameters: [[$class: 'StringParameterValue', name: 'prop', value: selectedFile]]
                }
            }
        }

        stage('run regression test') {
            steps {
                sh 'node_modules/.bin/testcafe chrome tests/**/* -r xunit:res.xml'
            }
        }
    }
}