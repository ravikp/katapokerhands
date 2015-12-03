package org.ravi.pokerhands.rules

import org.ravi.pokerhands.models.{Card, Hand, Result}
import org.ravi.pokerhands.rules.GameConstants._

object StraightFlush extends BaseRule {

  def isDefinedAt(hands: (Hand, Hand)): Boolean = {
    val (hand1, hand2) = hands
    canEvaluate(hand1, hand2)
  }

  def apply(hands: (Hand, Hand)): Result = {
    implicit val cardOrdering = Card.cardOrderingBasedOnValue
    val (hand1, hand2) = hands
    findHigher(hand1, hand2)
  }

  private val MESSAGE = "with straight flush"

  private def canEvaluate(hand1: Hand, hand2: Hand): Boolean = {
    IsStraightFlush(hand1) || IsStraightFlush(hand2)
  }

  private def findHigher(hand1: Hand, hand2: Hand): Result = {
    val winner = (IsStraightFlush(hand1), IsStraightFlush(hand2)) match {
      case (true, false) => hand1
      case (false, true) => hand2
      case (true, true) => resolveTie(hand1, hand2)
      case _ => throw new IllegalArgumentException("should not have come here, filter should have caught !!!")
    }
    Result(winner.name, MESSAGE)
  }

  private def IsStraightFlush(hand: Hand): Boolean = {
    val cards = hand.sortedCardsFromHighToLow
    val cardValues = cards map (_.value)

    //sum of differences between current and next element when elements are
    //sequence will be one less than the number of elements

    val sumOfCards = cardValues.sliding(2).map(x => x(0) - x(1)).sum
    val result = sumOfCards == TOTAL_CARDS_IN_HAND - 1 && cards.groupBy(x => x.suite).size == 1

    result
  }
  
  private def resolveTie(hand1: Hand, hand2: Hand):Hand = {
    val (cards1, cards2) = (
      hand1.sortedCardsFromHighToLow,
      hand2.sortedCardsFromHighToLow)

    val cardOrdering = Card.cardOrderingBasedOnValue

    def internalResolveTie(twoHands: Seq[(Card, Card)]):Hand = {
      twoHands match {
        case (card1, card2) :: tail if cardOrdering.gt(card1, card2) => hand1
        case (card1, card2) :: tail if cardOrdering.lt(card1, card2) => hand2
        case (card1, card2) :: tail if cardOrdering.eq(card1, card2) => internalResolveTie(tail)
      }
    }

    internalResolveTie(cards1.zip(cards2))
  }
}
