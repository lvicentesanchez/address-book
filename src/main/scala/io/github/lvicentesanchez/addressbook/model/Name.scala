package io.github.lvicentesanchez.addressbook.model

case class Name(first: String, surname: String)

object Name {
  def unapply(string: String): Option[Name] = None
}
