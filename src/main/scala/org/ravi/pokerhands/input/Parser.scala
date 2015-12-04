package org.ravi.pokerhands.input

import org.ravi.pokerhands.models.{Card, Player}

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.input.CharSequenceReader

/**
 * Created by ravikupin on 3/12/15.
 */

object ParseInput extends JavaTokenParsers {

  def lineItem:Parser[List[Player]] = rep(player)

  def player:Parser[Player] = (playername <~ ":") ~ cards ^^ {
    case (playername ~ cards) => Player(playername, cards)
  }

  def playername:Parser[String] = "[a-zA-Z]+".r

  def card:Parser[Card] = "[2-9TJQKA]".r ~ "[HDCS]".r ^^ {case value ~ cardtype => Card(value, cardtype)}

  def cards:Parser[List[Card]] = repN(5, card)

  val lineItemPhrase = phrase(lineItem)

  def apply(input: String):ParseResult[List[Player]] = lineItemPhrase(new CharSequenceReader(input))
}
