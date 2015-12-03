package org.ravi.pokerhands

import org.ravi.pokerhands.models.{Hand, CardType, Card}

import scala.collection.immutable.IndexedSeq

/**
 * Created by ravikupin on 1/12/15.
 */
trait TestData {
  val sequenceOfDiamonds = sequenceOfDiamondsStartingWith(1)_

  def sequenceOfDiamondsStartingWith(x: Int)(name: String):Hand = {
    Hand(name, (x to x + 4 map { x =>
      Card(x, CardType.Diamond)
    }).toList)
  }
}
