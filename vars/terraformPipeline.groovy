def call(Map conf = [:]) {
  String action = conf.action ?: "plan"

  node {
    stage('Test') {
      def result = sh(script: "terraform ${action}", returnStdout: true)
      echo("${result}")
    }
  }
}
