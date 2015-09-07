package io.github.lvicentesanchez.addressbook.model

sealed trait Gender

object Gender {
  def unapply(string: String): Option[Gender] = Female.unapply(string) orElse Male.unapply(string)
}

case object Female extends Gender {
  def unapply(string: String): Option[Female.type] = None
}

case object Male extends Gender {
  def unapply(string: String): Option[Male.type] = None
}
