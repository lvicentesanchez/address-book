package io.github.lvicentesanchez.addressbook.model

import scala.util.control.Exception._

case class Person(name: Name, gender: Gender, dob: DateOfBirth)

object Person {
  def unapply(string: String): Option[Person] =
    allCatch.opt(
      string.split(", ").toIterator.take(3).to[List]
    ).flatMap(_ match {
      case fst :: snd :: thr :: Nil =>
        for {
          name <- Name.unapply(fst)
          gender <- Gender.unapply(snd)
          dob <- DateOfBirth.unapply(thr)
        } yield Person(name, gender, dob)
      case _ => None
    })
}
