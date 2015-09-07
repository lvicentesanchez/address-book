package io.github.lvicentesanchez.addressbook.model

import org.specs2.Spec

class NameSpec extends Spec {
  def is = s2"""

      ordering should

        return -1 if nameA < nameB (lexicographically, surname first) $lowerThan
        return  0 if nameA = nameB (lexicographically, surname first) $equalTo
        return  1 if nameA > nameB (lexicographically, surname first) $greaterThan

      unapply should

       return None if string doesn't contain a valid name             $notValidNameString
       return Some(...) if string contains a valid date               $validNameString
   """

  def equalTo = {
    val nameA = Name("Luis", "Vicente")
    val nameB = Name("Luis", "Vicente")
    Ordering[Name].compare(nameA, nameB) must_== 0
  }

  def greaterThan = {
    val nameA = Name("Luis", "Vicente")
    val nameB = Name("Luis", "Bicente")
    Ordering[Name].compare(nameA, nameB) must_== 1
  }

  def lowerThan = {
    val nameA = Name("Luis", "Bicente")
    val nameB = Name("Luis", "Vicente")
    Ordering[Name].compare(nameA, nameB) must_== -1
  }

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
