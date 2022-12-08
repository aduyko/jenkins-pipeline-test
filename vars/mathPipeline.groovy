import org.rearc.MathUtil

def call(Map conf = [:]) {
  int num = conf.num ?: MathUtil.DEFAULT_NUM

  parameters([
    string(name: "NUM", defaultValue: num, description: "Number to increase")
  ])

  node {
    stage('Math') {
      def mathResult = MathUtil.addOne(params.NUM)
      echo("${mathResult}")
    }
  }
}
