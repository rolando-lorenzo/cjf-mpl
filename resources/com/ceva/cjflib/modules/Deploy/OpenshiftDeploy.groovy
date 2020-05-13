sh "mvn -U help:all-profiles deploy"
sh "mvn -U help:active-profiles clean compile site:site site:jar -Pcjf-generate-site -Dmaven.test.skip=true -Dcheckstyle.skip=true -Denforcer.skip=true"
     publishHTML target: [
             allowMissing: false,
             alwaysLinkToLastBuild: false,
             keepAll: true,
             reportDir: 'target/site',
             reportFiles: 'index.html',
             reportName: 'Project Doc Site'
            ]             