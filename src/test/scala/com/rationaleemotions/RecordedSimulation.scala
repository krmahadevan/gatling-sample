package com.rationaleemotions

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("http://localhost:9966")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.userAgentHeader("PostmanRuntime/7.28.4")

	val headers_0 = Map(
		"Cache-Control" -> "no-cache",
		"Postman-Token" -> "25f445f2-0e57-4573-88d7-420f59dc349f")



	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/petclinic/api/pets")
			.headers(headers_0))

//	setUp(
//		scn.inject(
//			nothingFor(5.seconds),
//			atOnceUsers(1),
//			rampUsers(1).during(5.seconds)
//		)
//	).protocols(httpProtocol)

	// generate a closed workload injection profile
	// with levels of 10, 15, 20, 25 and 30 concurrent users
	// each level lasting 10 seconds
	// separated by linear ramps lasting 10 seconds
	setUp(
		scn.inject(
			incrementConcurrentUsers(5) // Int
				.times(5)
				.eachLevelLasting(10.seconds)
				.separatedByRampsLasting(45.seconds)
				.startingFrom(10) // Int
		)
	).protocols(httpProtocol)

}