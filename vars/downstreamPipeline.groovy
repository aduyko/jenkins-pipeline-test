import org.rearc.MathUtil

def execute(Map conf = [:]) {
  String DOWNSTREAM_JOB = "/math-pipeline"
  int num = conf.num ?: MathUtil.random()

  node {
    stage('Build') {
      def job = build(
        job: DOWNSTREAM_JOB,
        parameters: [
          string(name: "NUM", value: num)
        ]
      )
      echo(job.getProjectName())
    }
  }
}

return this