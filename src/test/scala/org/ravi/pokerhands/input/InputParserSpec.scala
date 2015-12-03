package org.ravi.pokerhands.input

import org.ravi.pokerhands.models.CardType._
import org.ravi.pokerhands.models.{Card, Player}
import org.ravi.pokerhands.{SpecBase, TestData}

/**
 * Created by ravikupin on 3/12/15.
 */

class InputParserSpec extends SpecBase with TestData {

  describe("input with two hands") {
    it("should be parsed") {
      val input: String = "Black: 2H 3D 5S 9C 7D  White: 2C 3H 4S 8C AHWhite: 2C 3H 4S 8C AH"

      val parseResult = ParseInput(input)

      val player1 = Player("Black", List(Card(2, Heart), Card(3, Diamond), Card(5, Spade), Card(9, Club), Card(7, Diamond)))
      val player2 = Player("White", List(Card(2, Club), Card(3, Heart), Card(4, Spade), Card(8, Club), Card(14, Heart)))

      parseResult.get should be(List(player1, player2, player2))
    }
  }
}


