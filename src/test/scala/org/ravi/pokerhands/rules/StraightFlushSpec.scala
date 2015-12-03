package org.ravi.pokerhands.rules

import org.ravi.pokerhands.{TestData, SpecBase}
import org.ravi.pokerhands.models._

/**
 * Created by ravikupin on 1/12/15.
 */


class StraightFlushSpec extends SpecBase with TestData {

  describe("Straight flush") {
    val MESSAGE = "with straight flush"
    val player1 = sequenceOfDiamonds("player1")

    val player2 = Player("player2", List(Card(1, CardType.Club), Card(3, CardType.Heart), Card(5, CardType.Diamond), Card(7, CardType.Spade),
      Card(9, CardType.Club)
    ))

    describe("when first hand out of the two hands is straight flush") {
      it("should detect as straight flush") {
        StraightFlush.isDefinedAt(player1, player2) should be(true)
      }

      it("should declare first hand as winner") {
        StraightFlush(player1, player2) should be(Result(player1.name, MESSAGE))
      }
    }

    describe("when second hand out of the two hands is straight flush") {
      it("should detect as straight flush") {
        StraightFlush.isDefinedAt(player1, player2) should be(true)
      }

      it("should declare second hand as the winner") {
        StraightFlush(player2, player1) should be(Result(player1.name, MESSAGE))
      }
    }

    describe("when first hand do not contain sequence") {
      it("should not detect as straight flush") {
        val player1 = Player("player1", List(Card(1, CardType.Club), Card(3, CardType.Club), Card(5, CardType.Club), Card(7, CardType.Club),
          Card(9, CardType.Club)
        ))
        StraightFlush.isDefinedAt(player1, player2) should be(false)
      }
    }

    describe("when there is a tie") {
      it("should declare second hand as winner because second hand got highest first card") {
        val player2 = sequenceOfDiamondsStartingWith(6)("player2")
        StraightFlush(player1, player2) should be(Result(player2.name, MESSAGE))
      }
    }
  }
}
