package org.ravi.pokerhands.models

/**
 * Created by ravikupin on 1/12/15.
 */
case class Player(val name: String, val cards: List[Card]) {
  def sorted = cards.sorted.reverse
}
