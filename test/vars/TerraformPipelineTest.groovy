import com.lesfurets.jenkins.unit.*
import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.*

class TerraformPipelineTest extends BaseTest {
  Object terraformPipeline
  static final int TEST_NUM=3

  @BeforeEach
  void setUp() {
    super.setUp()
    terraformPipeline = loadScript("vars/terraformPipeline.groovy")

    helper.registerAllowedMethod('sh', [Map]) { args ->
      return args["script"] ?: "sh without script arg"
    }
  }

  @Test
  void testTerraformPipelineNoArgs() throws Exception {
    terraformPipeline()

    def shCalls = helper.callStack.findAll { it.methodName == "sh" }
    def echoCalls = helper.callStack.findAll { it.methodName == "echo" }

    // printCallStack()

    assertNotNull(shCalls)
    assert { shCalls.size() == 1 }
    assertEquals(shCalls[0].args[0]["script"].toString(), "terraform plan")

    assertNotNull(echoCalls)
    assert { echoCalls.size() == 1 }
    assertEquals(echoCalls[0].args[0], "terraform plan")

  }

  @Test
  void testTerraformPipelineArgs() throws Exception {
    terraformPipeline([
      action: "apply"
    ])

    def shCalls = helper.callStack.findAll { it.methodName == "sh" }
    def echoCalls = helper.callStack.findAll { it.methodName == "echo" }

    // printCallStack()

    assertNotNull(shCalls)
    assert { shCalls.size() == 1 }
    assertEquals(shCalls[0].args[0]["script"].toString(), "terraform apply")

    assertNotNull(echoCalls)
    assert { echoCalls.size() == 1 }
    assertEquals(echoCalls[0].args[0], "terraform apply")

  }
}