package simulation.APITest

import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocol

class CRUD extends Simulation {
  val httpProtocol: HttpProtocol = http.baseUrl("https://petstore.swagger.io")
  val postScn: ScenarioBuilder = scenario("Post Pet")
    .exec(session => session.set("pause", "4")).pause("#{pause}")
    .exec(
      http("Post Pet Request").post("/v2/pet")
        .body(RawFileBody("TestData/PostPet.json")).asJson
        .check(status is 200)
    )

  val getScn: ScenarioBuilder = scenario("Get Pet").pause(6).exec(
    http("Get Pet Request").get("/v2/pet/291493")
  )

  val deleteScn: ScenarioBuilder = scenario("Delete Pet").pause(6).exec(
    http("Delete Pet Request").delete("/v2/pet/291493")
  )

  setUp(
    postScn.inject(atOnceUsers(1)),
    getScn.inject(atOnceUsers(1)),
    deleteScn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)
}