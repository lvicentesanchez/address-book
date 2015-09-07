package io.github.lvicentesanchez.addressbook.model

import java.time.LocalDate

import org.specs2.Spec

class DateOfBirthSpec extends Spec {
  def is = s2"""
     DateOfBirth should

       return None if string doesn't contain a valid date $notValidDateString
       return Some(...) if string contains a valid date   $validDateString
   """

  def notValidDateString = {
    val string: String = "99/12/1915"
    val gender: Option[DateOfBirth] = DateOfBirth.unapply(string)
    gender must beNone
  }

  def validDateString = {
    val string: String = "01/12/1937"
    val gender: Option[DateOfBirth] = DateOfBirth.unapply(string)
    gender must beSome(LocalDate.of(1937, 12, 1))
  }
}
