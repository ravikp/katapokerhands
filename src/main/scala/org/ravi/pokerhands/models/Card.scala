package org.ravi.pokerhands.models

import org.ravi.pokerhands.models.CardType.Suite

case class Card(value: Int, suite: Suite){
}

object Card {

  implicit val cardOrderingBasedOnValue: Ordering[Card] = Ordering.by[Card, Int](x => x.value)

  def apply(value: String, cardtype: String): Card = {
    Card(valuemap(value), cardtypemap(cardtype))
  }

  val valuemap:Map[String, Int] = {
    val fromTwoToNine = 2 to 9 map (x => (x.toString, x)) toMap
    val result = fromTwoToNine ++ Map(
      "T" -> 10, "J" -> 11,
      "Q" -> 12, "K" -> 13,
      "A" -> 14
    )
    result
  }

  val cardtypemap:Map[String, Suite] = Map(
    "H" -> CardType.Heart,
    "D" -> CardType.Diamond,
    "C" -> CardType.Club,
    "S" -> CardType.Spade
  )
}

object CardType extends Enumeration {
  type Suite = Value
  val Club, Diamond, Heart, Spade = Value
}
