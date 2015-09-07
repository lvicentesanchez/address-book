package io.github.lvicentesanchez.addressbook.model

import io.github.lvicentesanchez.utils.time._

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import scala.util.control.Exception._

case class DateOfBirth(date: LocalDate) {
  override val toString: String = date.format(DateTimeFormatter.ofPattern("dd/MM/YY"))
}

object DateOfBirth extends DateOfBirthInstances {
  val BaseYear = 1900
  val Regexp = """^(\d{2})/(\d{2})/(\d{2})$""".r

  def unapply(string: String): Option[DateOfBirth] =
    allCatch.opt {
      val Regexp(day, month, year) = string
      DateOfBirth(LocalDate.of(BaseYear + year.toInt, month.toInt, day.toInt))
    }
}

trait DateOfBirthInstances {
  implicit val ordering: Ordering[DateOfBirth] = Ordering.by(_.date)
}
