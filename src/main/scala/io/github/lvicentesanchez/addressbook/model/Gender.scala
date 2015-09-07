package io.github.lvicentesanchez.addressbook.model

sealed trait Gender

object Gender extends GenderInstances {
  def unapply(string: String): Option[Gender] = Female.unapply(string) orElse Male.unapply(string)
}

trait GenderInstances {
  implicit val ordering: Ordering[Gender] = Ordering.by(_.toString)
}

case object Female extends Gender {
  override val toString: String = "Female"

  def unapply(string: String): Option[Female.type] =
    if (string.toUpperCase == "FEMALE") Some(Female) else None
}

case object Male extends Gender {
  override val toString: String = "Male"

  def unapply(string: String): Option[Male.type] =
    if (string.toUpperCase == "MALE") Some(Male) else None
}
