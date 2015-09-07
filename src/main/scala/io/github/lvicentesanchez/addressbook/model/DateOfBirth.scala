package io.github.lvicentesanchez.addressbook.model

import java.time.LocalDate

case class DateOfBirth(date: LocalDate) extends AnyVal

object DateOfBirth {
  def unapply(string: String): Option[DateOfBirth] = None
}
