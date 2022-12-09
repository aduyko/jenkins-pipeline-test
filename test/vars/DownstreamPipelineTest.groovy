import com.lesfurets.jenkins.unit.*
import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.*

import org.rearc.MathUtil

class DownstreamPipelineTest extends BasePipelineTest {
  Object downstreamPipeline
  static final int TEST_NUM=3

  @BeforeEach
  void setUp() {
    super.setUp()
    downstreamPipeline = loadScript("vars/downstreamPipeline.groovy")
  }

  @Test
  void testDownstreamPipelineNoArgs() throws Exception {
    downstreamPipeline()

    printCallStack()
  }

  @Test
  void testDownstreamPipelineWithArg() throws Exception {
    downstreamPipeline([
      num: this.TEST_NUM
    ])

    def echoCall = helper.callStack.find { it.methodName == "echo" }

    // assertNotNull(echoCall)
    // assert { echoCall.args.size() == 1 }
    // assertEquals(echoCall.args[0], MathUtil.addOne(this.TEST_NUM).toString())

    // printCallStack()
  }
}