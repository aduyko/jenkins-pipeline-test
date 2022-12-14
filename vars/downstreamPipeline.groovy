import org.rearc.MathUtil

def call(Map conf = [:]) {
  String DOWNSTREAM_JOB = "/math-pipeline"
  int num = conf.num ?: MathUtil.random()

  node {
    stage('Build') {
      echo("${num}")
      def job = build(
        job: DOWNSTREAM_JOB,
        parameters: [
          string(name: "NUM", value: "${num}")
        ]
      )
      echo(job.getProjectName())
    }
  }
}
