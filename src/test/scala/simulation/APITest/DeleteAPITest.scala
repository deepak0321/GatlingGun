package simulation.APITest

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class DeleteAPITest extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val scn : ScenarioBuilder = scenario("Delete Pet")
    .exec(http("Delete Pet Request").delete("/v2/pet/291493")
      .check(status is 200)
    )

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))


}
