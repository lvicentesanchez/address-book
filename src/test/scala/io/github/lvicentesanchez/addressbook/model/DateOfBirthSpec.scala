package io.github.lvicentesanchez.addressbook.model

import java.time.LocalDate

import org.specs2.Spec

class DateOfBirthSpec extends Spec {
  def is = s2"""

     ordering should

       return -1 if dobA < dobB                           $lowerThan
       return 0 if dobA = dobB                            $equalTo
       return 1 if dobA > dobB                            $greaterThan

     unapply should

       return None if string doesn't contain a valid date $notValidDateString
       return Some(...) if string contains a valid date   $validDateString
   """

  def equalTo = {
    val dobA = DateOfBirth(LocalDate.of(1910, 1, 1))
    val dobB = DateOfBirth(LocalDate.of(1910, 1, 1))
    Ordering[DateOfBirth].compare(dobA, dobB) must_== 0
  }

  def greaterThan = {
    val dobA = DateOfBirth(LocalDate.of(1910, 1, 1))
    val dobB = DateOfBirth(LocalDate.of(1909, 1, 1))
    Ordering[DateOfBirth].compare(dobA, dobB) must_== 1
  }

  def lowerThan = {
    val dobA = DateOfBirth(LocalDate.of(1909, 1, 1))
    val dobB = DateOfBirth(LocalDate.of(1910, 1, 1))
    Ordering[DateOfBirth].compare(dobA, dobB) must_== -1
  }

  def notValidDateString = {
    val string: String = "99/12/1915"
    val gender: Option[DateOfBirth] = DateOfBirth.unapply(string)
    gender must beNone
  }

  def validDateString = {
    val string: String = "01/12/15"
    val gender: Option[DateOfBirth] = DateOfBirth.unapply(string)
    gender must beSome(DateOfBirth(LocalDate.of(1915, 12, 1)))
  }
}
