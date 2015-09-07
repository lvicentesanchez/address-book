package io.github.lvicentesanchez.addressbook.model

import scala.util.control.Exception._

case class Name(first: String, surname: String) {

  override val toString: String = s"$first $surname"
}

object Name extends NameInstances {

  val Regexp = """^(\w*) (\w*)$""".r

  def unapply(string: String): Option[Name] =
    allCatch.opt {
      val Regexp(first, surname) = string
      Name(first, surname)
    }
}

trait NameInstances {

  implicit val ordering: Ordering[Name] = Ordering.by(name => (name.surname, name.first))
}
