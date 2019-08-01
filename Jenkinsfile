import static groovy.io.FileType.FILES

@NonCPS
def inputParamsString(dir) {
    def list = []
    dir.eachFileRecurse(FILES) {
        if(it.name.endsWith('.js')) {
            list << it.getName()
        }
    }
    list.join("\n")
}
pipeline {
    agent any

    tools { nodejs "regression" }

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
            node {
                git url: 'https://github.com/dartavion/testing-jenkins.git';
                def inputParams = inputParamsString(new File(pwd()))
                def selectedProperty = input(id: 'userInput', message: 'Choose properties file', parameters: [[$class: 'ChoiceParameterDefinition', choices: inputParams, description: 'Properties', name: 'prop']])
                println "Property: $selectedProperty"
                build job: 'usom-regression-tests', parameters: [[$class: 'StringParameterValue', name: 'prop', value: selectedProperty]]
            }
        }

        stage('run regression test') {
            steps {
                sh 'npm run test' $selectedProperty
            }
        }
    }
}