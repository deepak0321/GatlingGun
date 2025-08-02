package simulation.APITest

import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder

class PutAPITest extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val scn : ScenarioBuilder = scenario("Update Pet")
    .exec(http("Update Pet Request").put("/v2/pet")
      .body(StringBody(
        """
          |{
          |  "id": 200701055,
          |  "category": {
          |    "id": 0,
          |    "name": "string"
          |  },
          |  "name": "Deepak Raj",
          |  "photoUrls": [
          |    "string"
          |  ],
          |  "tags": [
          |    {
          |      "id": 0,
          |      "name": "string"
          |    }
          |  ],
          |  "status": "sold"
          |}
          |""".stripMargin)).asJson
      .check(status is 200)
    )

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))

}
