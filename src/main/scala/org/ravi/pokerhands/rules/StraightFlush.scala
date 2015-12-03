package org.ravi.pokerhands.rules

import org.ravi.pokerhands.models.{Card, Player, Result}
import org.ravi.pokerhands.rules.GameConstants._

object StraightFlush extends BaseRule {

  def isDefinedAt(players: (Player, Player)): Boolean = {
    val (player1, player2) = players
    canEvaluate(player1, player2)
  }

  def apply(players: (Player, Player)): Result = {
    implicit val cardOrdering = Card.cardOrderingBasedOnValue
    val (player1, player2) = players
    findHigher(player1, player2)
  }

  private val MESSAGE = "with straight flush"

  private def canEvaluate(player1: Player, player2: Player): Boolean = {
    IsStraightFlush(player1) || IsStraightFlush(player2)
  }

  private def findHigher(player1: Player, player2: Player): Result = {
    val winner = (IsStraightFlush(player1), IsStraightFlush(player2)) match {
      case (true, false) => player1
      case (false, true) => player2
      case (true, true) => resolveTie(player1, player2)
      case _ => throw new IllegalArgumentException("should not have come here, filter should have caught !!!")
    }
    Result(winner.name, MESSAGE)
  }

  private def IsStraightFlush(player: Player): Boolean = {
    val cards = player.sorted
    val cardValues = cards map (_.value)

    //sum of differences between current and next element when elements are
    //sequence will be one less than the number of elements

    val sumOfCards = cardValues.sliding(2).map(x => x(0) - x(1)).sum
    val result = sumOfCards == TOTAL_CARDS_IN_HAND - 1 && cards.groupBy(x => x.suite).size == 1

    result
  }
  
  private def resolveTie(player1: Player, player2: Player):Player = {
    val (cards1, cards2) = (
      player1.sorted,
      player2.sorted)

    val cardOrdering = Card.cardOrderingBasedOnValue

    def internalResolveTie(twoHands: Seq[(Card, Card)]):Player = {
      twoHands match {
        case (card1, card2) :: tail if cardOrdering.gt(card1, card2) => player1
        case (card1, card2) :: tail if cardOrdering.lt(card1, card2) => player2
        case (card1, card2) :: tail if cardOrdering.eq(card1, card2) => internalResolveTie(tail)
      }
    }

    internalResolveTie(cards1.zip(cards2))
  }
}
