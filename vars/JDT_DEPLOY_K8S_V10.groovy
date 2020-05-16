def call(body) {
  
  MPLInit();
  
   def MPL = MPLPipelineConfig(body, [
    modules: [
      CheckoutV10: [:]
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
      
      stage('CheckoutV10'){
            steps{
                MPLModule()
            }
      }
	 
     }
  }
}
