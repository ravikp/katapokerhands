package org.ravi.pokerhands.rules

import org.ravi.pokerhands._
import org.ravi.pokerhands.models.{Hand, Card, Result}

/**
 * Created by ravikupin on 1/12/15.
 */

object GameConstants {
  val TOTAL_CARDS_IN_HAND = 5
}

trait BaseRule extends PartialFunction[(Hand, Hand), Result]
