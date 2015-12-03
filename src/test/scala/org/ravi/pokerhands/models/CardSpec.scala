package org.ravi.pokerhands.models

import CardType._
import org.ravi.pokerhands.SpecBase

/**
 * Created by ravikupin on 1/12/15.
 */
class CardSpec extends SpecBase {
  describe("Card comparison"){
    
    implicit val cardOrdering = Card.cardOrderingBasedOnValue
    
    describe("when card2 value is greater than card1 value"){
      it("should report card2 greater than card1"){
        val card1 = Card(1, CardType.Club)
        val card2 = Card(2, CardType.Club)

        card2 should be > (card1)
      }
    }

    describe("when card1 value is greater than card2 value"){
      it("should report card1 greater than card1"){
        val card1 = Card(2, CardType.Club)
        val card2 = Card(1, CardType.Club)

        card1 should be > (card2)
      }
    }

    describe("when card1 value is same as card2 value"){
      it("should report card1 and card2 are same"){
        val card1 = Card(2, CardType.Club)
        val card2 = Card(2, CardType.Club)

        cardOrdering.compare(card1, card2) should be(0)
      }
    }
  }

  describe("Card construction"){
    describe("with card value"){
      it("should construct 'T' card"){
        var Card(value, cardType) = Card("T", "S")
        value should be(10)
      }

      it("should construct 'J' card"){
        var Card(value, cardType) = Card("J", "S")
        value should be(11)
      }

      it("should construct 'Q' card"){
        var Card(value, cardType) = Card("Q", "S")
        value should be(12)
      }

      it("should construct 'K' card"){
        var Card(value, cardType) = Card("K", "S")
        value should be(13)
      }

      it("should construct 'A' card"){
        var Card(value, cardType) = Card("A", "S")
        value should be(14)
      }

      it("should construct cards from 2 to 9"){
        val cards = 2 to 9 map {x => Card(x.toString, "S")}
        val cardvalues = cards.map(x => x.value)
        cardvalues should be(2 to 9 toList)
      }
    }
    describe("with card type"){
      it("should construct hearts"){
        var Card(value, cardType) = Card("2", "H")
        cardType should be(Heart)
      }

      it("should construct spades"){
        var Card(value, cardType) = Card("2", "S")
        cardType should be(Spade)
      }

      it("should construct diamonds"){
        var Card(value, cardType) = Card("2", "D")
        cardType should be(Diamond)
      }

      it("should construct clubs"){
        var Card(value, cardType) = Card("2", "C")
        cardType should be(Club)
      }
    }
  }
}
