node {
    checkout scm
    sh 'gradle setupCiWorkspace clean build'
    archive 'build/libs/*jar'
}