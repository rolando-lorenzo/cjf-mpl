def call(body) {
  
   def MPL = MPLPipelineConfig(body, [
    modules: [
      ManualBuild: [:],
      Checkout: [:],
	  Merge: [:],
      Build: [:],
	  Sonar: [:],
      Deploy: [:],
      Tag: [:]
    ]
  ])
 
   pipeline {  
    agent {
      label 'CEVA_LINUX'
    }
    tools {
        maven 'M3'
        jdk 'JDK8'  
    }
      	parameters{
		string(name: 'TAG', defaultValue: 'develop', description: 'Please enter the GIT branch or tag or commitid in this format.<br>tags/[tag-name]<br>[commitid]<br>[branch-name]')
		booleanParam(name: 'forceDeploy', defaultValue: false, description: 'Do you want to force the deployment?' )
    }
    stages {
      stage('ManualBuild'){
            when {
                  expression{
	    			  return env.gitlabUserName == null	    			
	    		}
         }
            steps{
                MPLModule()
            }
        }
      stage('Checkout'){
            steps{
                MPLModule()
            }
        }
	  stage('Merge'){
        	when {
                  expression{
	    			  return env.gitlabActionType == 'MERGE' && env.gitlabMergeRequestState != 'merged'
	    		}
         }
            steps{
                MPLModule()
            }
	    }
      stage('Build'){
            steps{
                MPLModule()
            }
	    }
	  stage('Sonar'){
            steps{
                MPLModule()
            }
        }
	  stage('Deploy'){
		    when {
                  expression{
                      return env.gitlabBranch == 'develop' || env.gitlabBranch ==~ /release.*/ || params.forceDeploy == true
                }
        }
            steps{
	    	    MPLModule()
			}
	    }
	  stage('Tag'){
		    when {
                  expression{
                      return env.gitlabBranch == 'develop' || env.gitlabBranch ==~ /release.*/ || params.forceDeploy == true
                }
        }
            steps{
                MPLModule()
            }
        }
     }
  }
}
