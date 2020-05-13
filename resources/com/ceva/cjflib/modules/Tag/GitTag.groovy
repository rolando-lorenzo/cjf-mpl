//	sh "git tag -a ci-${JOB_BASE_NAME}-${BUILD_NUMBER} -m Jenkins"
                
                withCredentials([usernamePassword(credentialsId: 'ci.server', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                  //sh("git config user.name 'Jenkins'")
                  //sh("git config user.email 'jenkins@cevalogistics.com'")
                  sh "git config remote.origin.url ${CFG.'tag.tagurl'}"
                  sh "git push origin --tags"
               }