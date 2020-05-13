sh "echo 'Sonar Analysis'"
script {
      try {
          withSonarQubeEnv('SonarQube') {
          sh "mvn sonar:sonar"
             } 
         }catch (err) {
          echo currentBuild.result
         }
     }