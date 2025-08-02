package simulation.APITest

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder


class CheckResponseCode extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val scn : ScenarioBuilder = scenario("Check Response Code")
    .exec(
      http("Check Response").get("/v2/pet/findByStatus?status=sold")
        .check(status is 200)
        .check(jsonPath("$..tags[?(@.id==811)].id").exists)
    )

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
