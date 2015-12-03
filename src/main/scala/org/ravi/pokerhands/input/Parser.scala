package org.ravi.pokerhands.input

import org.ravi.pokerhands.models.{Card, Hand}

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.input.CharSequenceReader

/**
 * Created by ravikupin on 3/12/15.
 */

object Parsing extends JavaTokenParsers {
  def lineItem:Parser[List[Hand]] = rep(hand)
  def hand = (handname <~ ":") ~ cards ^^ {
    case (handname ~ cards) => Hand(handname, cards)
  }
  def handname = "[a-zA-Z]+".r
  def card = "[2-9TJQKA]".r ~ "[HDCS]".r ^^ {case value ~ cardtype => Card(value, cardtype)}
  def cards = repN(5, card)

  val lineItemPhrase = phrase(lineItem)

  def apply(input: String):ParseResult[List[Hand]] = lineItemPhrase(new CharSequenceReader(input))
}
