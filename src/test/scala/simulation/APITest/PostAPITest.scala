package simulation.APITest

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder


class PostAPITest extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val scn : ScenarioBuilder = scenario("Post Pet")
    .exec(http("Post Pet Request").post("/v2/pet")
      .body(RawFileBody("TestData/PostPet.json")).asJson
      .header("content-type","application/json")
    )

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
