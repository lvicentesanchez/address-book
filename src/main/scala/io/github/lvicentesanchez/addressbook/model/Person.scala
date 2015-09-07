package io.github.lvicentesanchez.addressbook.model

case class Person(name: Name, gender: Gender, dob: DateOfBirth)

object Person {
  def unapply(string: String): Option[Person] = None
}
