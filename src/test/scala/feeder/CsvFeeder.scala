package feeder

import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.protocol.HttpProtocolBuilder

class CsvFeeder extends Simulation{

  val httpProtocol: HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val csvFeeder = csv("Feeder/petData.csv").circular()

  def getPet(): ChainBuilder = {
    feed(csvFeeder)
      .exec(http("Get Pet Request With ID")
      .get("/v2/pet/#{id}")
      .check(status is 200, jsonPath("$.name") is "#{name}")
      )
  }

  val scn : ScenarioBuilder = scenario("Get Pet Data From CSV").exec(getPet())

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
