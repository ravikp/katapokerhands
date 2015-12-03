package org.ravi.pokerhands

import org.ravi.pokerhands.models.{Card, CardType, Player}

/**
 * Created by ravikupin on 1/12/15.
 */
trait TestData {
  val sequenceOfDiamonds = sequenceOfDiamondsStartingWith(1)_

  def sequenceOfDiamondsStartingWith(x: Int)(name: String):Player = {
    Player(name, (x to x + 4 map { x =>
      Card(x, CardType.Diamond)
    }).toList)
  }
}
