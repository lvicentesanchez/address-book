package io.github.lvicentesanchez.addressbook.model

import java.time.LocalDate

import org.specs2.Spec

class PersonSpec extends Spec {
  def is = s2"""
     Person should

       return None if string doesn't contain a valid person $notValidPersonString
       return Some(...) if string contains a valid person   $validParsonString
   """

  def notValidPersonString = {
    val string: String = "Luis Vicente, Male, 79/02/16"
    val gender: Option[Name] = Name.unapply(string)
    gender must beNone
  }

  def validParsonString = {
    val string: String = "Luis Vicente, Male, 16/02/79"
    val gender: Option[Name] = Name.unapply(string)
    gender must beSome(Person(Name("Luis", "Vicente"), Male, DateOfBirth(LocalDate.of(1979, 2, 16))))
  }
}
