package org.ravi.pokerhands.models

/**
 * Created by ravikupin on 1/12/15.
 */
case class Hand(val cards: List[Card], val name: String){
  def sortedCardsFromHighToLow = cards.sorted.reverse
}

object Hand {
  def apply(name: String, cards: List[Card]) = new Hand(cards.toList, name)
}
