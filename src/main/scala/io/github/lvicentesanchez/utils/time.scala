package io.github.lvicentesanchez.utils

import java.time.LocalDate

object time {

  implicit val localDateOrdering: Ordering[LocalDate] = new Ordering[LocalDate] {

    override def compare(x: LocalDate, y: LocalDate): Int = x.compareTo(y)
  }
}
