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
                    def workspace = pwd()
                    println "PATH::::::: $workspace"
                    //${workspace} will now contain an absolute path to job workspace on slave
                    git url: 'https://github.com/dartavion/testing-jenkins.git'
                    def getGitFileList = load('getGitFileList.groovy')
                    def fileList = getGitFileList.inputParamsString(new File(pwd()))
                    def selectedFile = input(id: 'userInput', message: 'Choose properties file', parameters: [[$class: 'ChoiceParameterDefinition', choices: fileList, description: 'Properties', name: 'prop']])
                    println "Property: $selectedFile"
//                    build job: 'regression-pipeline', parameters: [[$class: 'StringParameterValue', name: 'prop', value: selectedFile]]
                }
            }
        }

        stage('run regression test') {
            steps {
                sh 'node_modules/.bin/testcafe chrome /**/* -r xunit:res.xml'
            }
        }
    }
}