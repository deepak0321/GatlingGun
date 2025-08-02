package simulation.APITest

import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder

class SaveResponseJSON extends Simulation {

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val scn : ScenarioBuilder = scenario("Check Response and Save data")
    .exec(
      http("Get a Pet Having Status Sold")
        .get("/v2/pet/findByStatus?status=sold")
        .check(status is 200)
        .check(jsonPath("$[0].id").saveAs("petID"))
        .check(jsonPath("$[0].category.name").saveAs("categoryName"))
    )
    .pause(10)
    .exec(
      http("Get Pet").get("/v2/pet/#{petID}")
        .check(
          status.in (200 to 204),
          jsonPath("$.id") is "#{petID}",
          jsonPath("$.category.name") is "#{categoryName}"
        )
    )
  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
