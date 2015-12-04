package org.ravi.pokerhands.rules

import org.ravi.pokerhands.models.{Card, Player, Result}

object StraightFlush extends BaseRule with Straight {

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

  override def canEvaluate(player1: Player, player2: Player): Boolean = {
    IsStraightFlush(player1) || IsStraightFlush(player2)
  }

  private def findHigher(player1: Player, player2: Player): Result = {
    val winner = (IsStraightFlush(player1), IsStraightFlush(player2)) match {
      case (true, false) => player1.name
      case (false, true) => player2.name
      case (true, true) => resolveTie(player1, player2)._1
      case _ => throw new IllegalArgumentException("should not have come here, filter should have caught !!!")
    }
    Result(winner, MESSAGE)
  }

  private def IsStraightFlush(player: Player): Boolean = {
    IsStraight(player) && IsFlush(player)
  }

  def IsFlush(player: Player): Boolean = {
    player.cards.groupBy(x => x.suite).size == 1
  }

  private def resolveTie(player1: Player, player2: Player): (String, String) = {
    val (cards1, cards2) = (
      player1.sorted,
      player2.sorted)

    val cardOrdering = Ordering.Iterable[Card]
    cardOrdering.compare(cards1, cards2) match {
      case -1 =>  (player2.name, "TODO: no reason")    //cards1 < cards2
      case  0 =>  ("TIE", "")                          //cards1 == cards2
      case  1 =>  (player1.name, "TODO: no reason")    //cards1 > cards2
    }
  }
}
