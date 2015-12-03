package org.ravi.pokerhands

import org.scalatest._

/**
 * Created by ravikupin on 1/12/15.
 */
class PartialFunctionTest extends FlatSpec with ShouldMatchers {

  it should "compose functions to test " in {
        val composed = CanAcceptLowercaseOnly orElse CanAcceptUppercaseOnly
        composed("ravi", "kumar") should be("KUMAR")
  }

  it should "slide pairs" in {
    val x = List(1,2,3)
    val window: Iterator[List[Int]] = x.sliding(2)
    val result = window.map(x => (x(0),x(1))).toList
    result(0) should be(1,2)
    result(1) should be(2,3)
  }

  object CanAcceptLowercaseOnly extends PartialFunction[(String, String), String] {
    def isDefinedAt(data: (String, String)) = data._1.toLowerCase == data._1
    def apply(data: (String,String)) = data._2.toUpperCase
  }

  object CanAcceptUppercaseOnly extends PartialFunction[(String, String), String] {
    def isDefinedAt(data: (String, String)) = data._1.toUpperCase == data._1
    def apply(data: (String, String)) = data._2.toLowerCase
  }
}
