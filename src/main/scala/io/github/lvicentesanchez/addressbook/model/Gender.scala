package io.github.lvicentesanchez.addressbook.model

sealed trait Gender

object Gender {
  def unapply(string: String): Option[Gender] = Female.unapply(string) orElse Male.unapply(string)
}

case object Female extends Gender {
  def unapply(string: String): Option[Female.type] =
    if (string.toUpperCase == "FEMALE") Some(Female) else None
}

case object Male extends Gender {
  def unapply(string: String): Option[Male.type] =
    if (string.toUpperCase == "MALE") Some(Male) else None
}
