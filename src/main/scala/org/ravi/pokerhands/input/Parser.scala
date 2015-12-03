package org.ravi.pokerhands.input

import org.ravi.pokerhands.models.{Card, Player}

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.input.CharSequenceReader

/**
 * Created by ravikupin on 3/12/15.
 */

object ParseInput extends JavaTokenParsers {
  def lineItem:Parser[List[Player]] = rep(hand)
  def hand = (playername <~ ":") ~ cards ^^ {
    case (playername ~ cards) => Player(playername, cards)
  }
  def playername = "[a-zA-Z]+".r
  def card = "[2-9TJQKA]".r ~ "[HDCS]".r ^^ {case value ~ cardtype => Card(value, cardtype)}
  def cards = repN(5, card)

  val lineItemPhrase = phrase(lineItem)

  def apply(input: String):ParseResult[List[Player]] = lineItemPhrase(new CharSequenceReader(input))
}
