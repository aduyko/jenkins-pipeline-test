import org.rearc.MathUtil

def call(Map conf = [:]) {
  int num = conf.num ?: MathUtil.DEFAULT_NUM

  properties([
    parameters([
      string(name: "NUM", defaultValue: "${num}", description: "Number to increase")
    ])
  ])

  node {
    stage('Math') {
      def mathResult = MathUtil.addOne(params.NUM.toInteger())
      echo("${mathResult}")
    }
  }
}
