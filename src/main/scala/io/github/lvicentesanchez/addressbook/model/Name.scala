package io.github.lvicentesanchez.addressbook.model

import scala.util.control.Exception._

case class Name(first: String, surname: String)

object Name {
  val Regexp = """^(\w*) (\w*)$""".r

  def unapply(string: String): Option[Name] =
    allCatch.opt {
      val Regexp(first, surname) = string
      Name(first, surname)
    }
}
