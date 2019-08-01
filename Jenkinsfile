selectedFile = ''
pipeline {
    agent any

    tools { nodejs "lts" }

    stages {

        stage('\\u2705 Cloning Git') {
            steps {
                git 'https://github.com/dartavion/testing-jenkins.git'
            }
        }
        stage('\\u2705 Install dependencies') {
            steps {
                sh 'npm install'
            }
        }
        stage('\\u2705 Pick Which Test to Run') {
            steps {
                script {
                    git url: 'https://github.com/dartavion/testing-jenkins.git'
                    def getGitFileList = load('getGitFileList.groovy')
                    def fileList = getGitFileList.inputParamsString(new File(pwd() + '/tests'))
                    
                    selectedFile = input(id: 'userInput', message: 'Test Cafe', parameters: [[$class: 'ChoiceParameterDefinition', choices: fileList, description: 'Select All to run all tests', name: 'Please Select a Test Cafe File']])
                    println "Property: $selectedFile"
                    // build job: 'regression-pipeline', parameters: [[$class: 'StringParameterValue', name: 'prop', value: selectedFile]]
                }
            }
        }

        stage('run regression test') {
            steps {
                sh "node_modules/.bin/testcafe chrome:headless tests/$selectedFile -r xunit:res.xml"
            }
        }
    }
    post {
        always {
            junit "res.xml"
        }
    }
}