/**
 * Git Checkout
 */

sh 'printenv'
//sh "export no_proxy=.logistics.corp"

    checkout([
                    $class: 'GitSCM', 
                    branches : [[name: "${env.gitlabSourceBranch}"]],
                    doGenerateSubmoduleConfigurations: false, 
                    extensions: [[$class: 'LocalBranch', localBranch: "**"]], 
                    submoduleCfg: [], 
                    userRemoteConfigs: [
                        [
                            url: CFG.'git.url' ,
                            credentialsId: 'ci.server'

                        ]
                    ]
                ])
    

sh "git branch"