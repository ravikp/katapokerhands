package org.ravi.pokerhands.rules

import org.ravi.pokerhands.{TestData, SpecBase}
import org.ravi.pokerhands.models.{Hand, Card, CardType, Result}

/**
 * Created by ravikupin on 1/12/15.
 */


class StraightFlushSpec extends SpecBase with TestData {

  describe("Straight flush") {
    val MESSAGE = "with straight flush"
    val hand1 = sequenceOfDiamonds("hand1")

    val hand2 = Hand("hand2", List(Card(1, CardType.Club), Card(3, CardType.Heart), Card(5, CardType.Diamond), Card(7, CardType.Spade),
      Card(9, CardType.Club)
    ))

    describe("when first hand out of the two hands is straight flush") {
      it("should detect as straight flush") {
        StraightFlush.isDefinedAt(hand1, hand2) should be(true)
      }

      it("should declare first hand as winner") {
        StraightFlush(hand1, hand2) should be(Result(hand1.name, MESSAGE))
      }
    }

    describe("when second hand out of the two hands is straight flush") {
      it("should detect as straight flush") {
        StraightFlush.isDefinedAt(hand1, hand2) should be(true)
      }

      it("should declare second hand as the winner") {
        StraightFlush(hand2, hand1) should be(Result(hand1.name, MESSAGE))
      }
    }

    describe("when first hand do not contain sequence") {
      it("should not detect as straight flush") {
        val hand1 = Hand("hand1", List(Card(1, CardType.Club), Card(3, CardType.Club), Card(5, CardType.Club), Card(7, CardType.Club),
          Card(9, CardType.Club)
        ))
        StraightFlush.isDefinedAt(hand1, hand2) should be(false)
      }
    }

    describe("when there is a tie") {
      it("should declare second hand as winner because second hand got highest first card") {
        val hand2 = sequenceOfDiamondsStartingWith(6)("hand2")
        StraightFlush(hand1, hand2) should be(Result(hand2.name, MESSAGE))
      }
    }
  }
}
