package io.gatling.demo

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class SauceDemoRecordedSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://www.saucedemo.com")
    .inferHtmlResources()
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36")
  
  private val headers_0 = Map(
  		"Accept" -> "*/*",
  		"Accept-Encoding" -> "gzip, deflate, br, zstd",
  		"Accept-Language" -> "en-IN,en-GB;q=0.9,en-US;q=0.8,en;q=0.7,ta;q=0.6",
  		"Access-Control-Request-Headers" -> "content-type",
  		"Access-Control-Request-Method" -> "POST",
  		"Origin" -> "https://www.saucedemo.com",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_2 = Map(
  		"Accept" -> "*/*",
  		"Accept-Encoding" -> "gzip, deflate, br, zstd",
  		"Accept-Language" -> "en-IN,en-GB;q=0.9,en-US;q=0.8,en;q=0.7,ta;q=0.6",
  		"Content-type" -> "application/json",
  		"Origin" -> "https://www.saucedemo.com",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"sec-ch-ua" -> """Not)A;Brand";v="8", "Chromium";v="138", "Google Chrome";v="138""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "Windows"
  )
  
  private val headers_4 = Map(
  		"sec-ch-ua" -> """Not)A;Brand";v="8", "Chromium";v="138", "Google Chrome";v="138""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "Windows"
  )
  
  private val uri1 = "https://events.backtrace.io/api"

  private val scn = scenario("SauceDemoRecordedSimulation")
    .exec(
      http("request_0")
        .options(uri1 + "/unique-events/submit?universe=UNIVERSE&token=TOKEN")
        .headers(headers_0)
        .resources(
          http("request_1")
            .options(uri1 + "/summed-events/submit?universe=UNIVERSE&token=TOKEN")
            .headers(headers_0),
          http("request_2")
            .post(uri1 + "/unique-events/submit?universe=UNIVERSE&token=TOKEN")
            .headers(headers_2)
            .body(RawFileBody("io/gatling/demo/saucedemorecordedsimulation/0002_request.json"))
            .check(status.is(401)),
          http("request_3")
            .post(uri1 + "/summed-events/submit?universe=UNIVERSE&token=TOKEN")
            .headers(headers_2)
            .body(RawFileBody("io/gatling/demo/saucedemorecordedsimulation/0003_request.json"))
            .check(status.is(401))
        ),
      pause(16),
      http("request_4")
        .get("/static/media/menu3x.52cc17a3.svg")
        .headers(headers_4)
        .resources(
          http("request_5")
            .get("/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg")
            .headers(headers_4),
          http("request_6")
            .get("/static/media/close@3x.a30a8a1d.svg")
            .headers(headers_4),
          http("request_7")
            .get("/static/media/cart3x.3669d74a.svg")
            .headers(headers_4),
          http("request_8")
            .get("/static/media/filter3x.2943df5b.svg")
            .headers(headers_4),
          http("request_9")
            .get("/static/media/arrow3x.4b825b78.svg")
            .headers(headers_4),
          http("request_10")
            .get("/static/media/bike-light-1200x1500.37c843b0.jpg")
            .headers(headers_4),
          http("request_11")
            .get("/static/media/bolt-shirt-1200x1500.c2599ac5.jpg")
            .headers(headers_4),
          http("request_12")
            .get("/static/media/sauce-pullover-1200x1500.51d7ffaf.jpg")
            .headers(headers_4),
          http("request_13")
            .get("/static/media/red-onesie-1200x1500.2ec615b2.jpg")
            .headers(headers_4),
          http("request_14")
            .get("/static/media/red-tatt-1200x1500.30dadef4.jpg")
            .headers(headers_4)
        )
    )

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
