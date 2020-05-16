def call(body) {
  
  MPLInit();
  
   def MPL = MPLPipelineConfig(body, [
    modules: [
      Checkout: [:],
      Build: [:],
      Deploy: [:],
      Tag: [:]
    ]
  ])
 
   pipeline {  
    agent any
    tools {
        maven 'Maven 3'
        jdk 'JDK8'  
    }
      	parameters{
		string(name: 'TAG', defaultValue: 'develop', description: 'Please enter the GIT branch or tag or commitid in this format.<br>tags/[tag-name]<br>[commitid]<br>[branch-name]')
		booleanParam(name: 'forceDeploy', defaultValue: false, description: 'Do you want to force the deployment?' )
    }
    stages {
      
      stage('Checkout'){
            steps{
                MPLModule()
            }
        }
	  
      stage('Build'){
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
