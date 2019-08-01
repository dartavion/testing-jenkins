selectedFile = ''
pipeline {
    agent any

    tools { nodejs "lts" }

    stages {

        stage('\u2705 Cloning Git') {
            steps {
                git 'https://github.com/dartavion/testing-jenkins.git'
            }
        }
        stage('\u9853 Install dependencies') {
            steps {
                sh 'npm install'
            }
        }
        stage('\u2705 Pick Which Test to Run') {
            steps {
                script {
                    git url: 'https://github.com/dartavion/testing-jenkins.git'
                    def getGitFileList = load('getGitFileList.groovy')
                    def fileList = getGitFileList.inputParamsString(new File(pwd() + '/tests'))
                    def INPUT_PARAMS = input message: 'Regression Tests', ok: 'Ok', cancel: 'Cancel',
                            parameters: [
                                choice(name: 'ENVIRONMENT', choices: ['Staging','Development'].join('\n'), description: 'Please select the Environment'),
                                choice(name: 'E2E_TEST', choices: fileList, message: 'Test Cafe', description: 'Please Select a Test')
                            ]

                    env.ENVIRONMENT = INPUT_PARAMS.ENVIRONMENT
                    env.E2E_TEST = INPUT_PARAMS.E2E_TEST
                }
            }
        }

        stage('run regression test') {
            steps {
                sh "node_modules/.bin/testcafe chrome:headless tests/${env.E2E_Test} -r xunit:res.xml"
            }
        }
    }
    post {
        always {
            junit "res.xml"
        }
    }
}
