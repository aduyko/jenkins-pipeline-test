import com.lesfurets.jenkins.unit.*
import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.*

import org.rearc.MathUtil

class MathPipelineTest extends BasePipelineTest {
  Object mathPipeline
  static final int TEST_NUM = 3

  @BeforeEach
  void setUp() {
    super.setUp()
    mathPipeline = loadScript("vars/mathPipeline.groovy")
  }

  @Test
  void testMathPipeline() throws Exception {
    mathPipeline()

    def echoCall = helper.callStack.find { it.methodName == "echo" }

    assertNotNull(echoCall)
    assert { echoCall.args.size() == 1 }
    assertEquals(echoCall.args[0], MathUtil.addOne(MathUtil.DEFAULT_NUM).toString())

    // printCallStack()
  }

  @Test
  void testMathPipelineDefaultArg() throws Exception {
    mathPipeline([
      num: this.TEST_NUM
    ])

    def echoCall = helper.callStack.find { it.methodName == "echo" }

    assertNotNull(echoCall)
    assert { echoCall.args.size() == 1 }
    assertEquals(echoCall.args[0], MathUtil.addOne(this.TEST_NUM).toString())

    // printCallStack()
  }

  @Test
  void testMathPipelineParams() throws Exception {
    binding.setVariable('params', ['NUM': this.TEST_NUM])

    mathPipeline()

    def echoCall = helper.callStack.find { it.methodName == "echo" }

    assertNotNull(echoCall)
    assert { echoCall.args.size() == 1 }
    assertEquals(echoCall.args[0], MathUtil.addOne(this.TEST_NUM).toString())

    // printCallStack()
  }
}