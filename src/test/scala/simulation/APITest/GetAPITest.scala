package simulation.APITest

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class GetAPITest extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val scn: ScenarioBuilder = scenario(name = "Get Pet").exec(http(requestName = "Get Pet Request").get("/v2/pet/10"))

  setUp(scn.inject(atOnceUsers(1000)).protocols(httpProtocol))

}
