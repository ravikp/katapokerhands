package org.ravi.pokerhands.app

import org.ravi.pokerhands.input.ParseInput

/**
 * Created by ravikupin on 3/12/15.
 */
object Program {
  def main(args: Array[String]) {
    args.toList match {
      case head :: Nil => println("Result: " + ParseInput(args(0)).getOrElse(None))
      case _ => println("No input !!!; may be you forgot to keep in double quotes.")
    }
  }
}
