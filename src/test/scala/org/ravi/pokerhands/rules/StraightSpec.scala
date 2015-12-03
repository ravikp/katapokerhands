package org.ravi.pokerhands.rules

import org.ravi.pokerhands.input.ParseInput
import org.ravi.pokerhands.{SpecBase, TestData}

/**
 * Created by ravikupin on 3/12/15.
 */
class StraightSpec extends SpecBase with TestData with Straight{
  describe("Straight cards - five cards with consecutive values"){
    it("should be able to detect one of the players as straight cards"){
      val player :: Nil = ParseInput("Black: 2H 3D 4S 5C 6D").get
      canEvaluate(player, player) should be(true)
    }

    it("should not detect as straight cards since no consecutive cards"){
      val player :: Nil = ParseInput("Black: 2H 3D 4S 7C 6D").get
      canEvaluate(player, player) should be(false)
    }

    it("should detect as straight cards since second player as consecutive cards"){
      val player1 :: player2 :: Nil = ParseInput("Black: 2H 3D 4S 7C 6D White: 2H 3D 4S 5C 6D ").get
      canEvaluate(player1, player2) should be(true)
    }

    it("should not detect straight cards since no player as consecutive cards"){
      val player1 :: player2 :: Nil = ParseInput("Black: 2H 3D 4S 7C 6D White: 2H 3D 4S 8C 6D ").get
      canEvaluate(player1, player2) should be(false)
    }
  }
}
