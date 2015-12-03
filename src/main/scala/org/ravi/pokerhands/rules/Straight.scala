package org.ravi.pokerhands.rules

import org.ravi.pokerhands.models.Player
import org.ravi.pokerhands.rules.GameConstants._

/**
 * Created by ravikupin on 3/12/15.
 */

trait Straight {
  def canEvaluate(player1: Player, player2: Player):Boolean = IsStraight(player1) || IsStraight(player2)

  def IsStraight(player: Player): Boolean = {
    val cards = player.sorted
    val cardValues = cards map (_.value)

    //sum of differences between current and next element when elements are
    //sequence will be one less than the number of elements

    val sumOfCards = cardValues.sliding(2).map(x => x(0) - x(1)).sum
    val result = sumOfCards == TOTAL_CARDS_IN_HAND - 1
    result
  }
}
