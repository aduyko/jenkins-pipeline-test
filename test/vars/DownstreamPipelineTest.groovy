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

    def echoCalls = helper.callStack.findAll { it.methodName == "echo" }

    assertNotNull(echoCalls)
    assert { echoCalls.size() == 2 }
    assertEquals(echoCalls[1].args[0], "some_job")

    // printCallStack()
  }

  @Test
  void testDownstreamPipelineWithArg() throws Exception {
    downstreamPipeline([
      num: this.TEST_NUM
    ])

    def echoCalls = helper.callStack.findAll { it.methodName == "echo" }

    assertNotNull(echoCalls)
    assert { echoCalls.args.size() == 2 }
    assertEquals(echoCalls[0].args[0], this.TEST_NUM.toString())
    assertEquals(echoCalls[1].args[0], "some_job")

    // printCallStack()
  }
}