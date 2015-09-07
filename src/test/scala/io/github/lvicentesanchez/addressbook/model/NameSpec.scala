package io.github.lvicentesanchez.addressbook.model

import java.time.LocalDate

import org.specs2.Spec

class NameSpec extends Spec {
  def is = s2"""
     Name should

       return None if string doesn't contain a valid name $notValidNameString
       return Some(...) if string contains a valid date   $validNameString
   """

  def notValidNameString = {
    val string: String = "  Fred   Flintstone"
    val gender: Option[Name] = Name.unapply(string)
    gender must beNone
  }

  def validNameString = {
    val string: String = "Fred Flintstone"
    val gender: Option[Name] = Name.unapply(string)
    gender must beSome(Name("Fred", "Flintstone"))
  }
}
