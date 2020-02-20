pipeline {
  agent any
  environment {
    registry = "orsilarssen/coderage2020"
    registryCredential = 'docOL'
    dockerImage = ''
    ENV_NAME = "CoderageHackathon-env"
    S3_BUCKET = "elasticbeanstalk-eu-central-1-124429370407"
    APP_NAME = 'CodeRage-Hackathon'
  }
   stages {
    stage('Gradle Build') {
      steps {
        sh './gradlew build --rerun-tasks'
      }
    }
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry + ":latest"
        }
      }
    }
    stage('Deploy Image') {
      steps{
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
          }
        }
      }
    }
    stage('Deploy to AWS') {
      steps {
        withAWS(credentials:'malachite2', region: 'eu-central-1') {
          sh 'aws s3 cp ./Dockerrun.aws.json \
          s3://$S3_BUCKET/$BUILD_ID/Dockerrun.aws.json'
          sh 'aws elasticbeanstalk create-application-version \
          --application-name "$APP_NAME" \
          --version-label CodeRage-Hackathon-$BUILD_ID \
          --source-bundle S3Bucket="$S3_BUCKET",S3Key="$BUILD_ID/Dockerrun.aws.json" \
          --auto-create-application'
          sh 'aws elasticbeanstalk update-environment \
          --application-name "$APP_NAME" \
          --environment-name $ENV_NAME \
          --version-label CodeRage-Hackathon-$BUILD_ID'
        }
      }
    }
  }
}