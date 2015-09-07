package io.github.lvicentesanchez.addressbook.model

import java.time.LocalDate

import scala.util.control.Exception._

case class DateOfBirth(date: LocalDate) extends AnyVal

object DateOfBirth {
  val BaseYear = 1900
  val Regexp = """^(\d{2})/(\d{2})/(\d{2})$""".r

  def unapply(string: String): Option[DateOfBirth] =
    allCatch.opt {
      val Regexp(day, month, year) = string
      DateOfBirth(LocalDate.of(BaseYear + year.toInt, month.toInt, day.toInt))
    }
}
