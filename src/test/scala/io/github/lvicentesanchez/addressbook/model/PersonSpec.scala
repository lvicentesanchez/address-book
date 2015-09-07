package io.github.lvicentesanchez.addressbook.model

import java.time.LocalDate

import org.specs2.Spec

class PersonSpec extends Spec {
  def is = s2"""

    ordering should

      compare by date of birth, if distinct                 $compareByDateOfBirth
      compare by name, if same date of birth                $compareByName
      compare by gender, if same date of birth and name     $compareByGender

     unapply should

       return None if string doesn't contain a valid person $notValidPersonString
       return Some(...) if string contains a valid person   $validParsonString
   """

  def compareByDateOfBirth = {
    val personA = Person(Name("Luis", "Vicente"), Male, DateOfBirth(LocalDate.of(1978, 2, 16)))
    val personB = Person(Name("Luis", "Vicente"), Male, DateOfBirth(LocalDate.of(1979, 2, 16)))
    Ordering[Person].compare(personA, personB) must_== -1
  }

  def compareByGender = {
    val personA = Person(Name("Luis", "Vicente"), Female, DateOfBirth(LocalDate.of(1979, 2, 16)))
    val personB = Person(Name("Luis", "Vicente"), Male, DateOfBirth(LocalDate.of(1979, 2, 16)))
    Ordering[Person].compare(personA, personB) must_== -1
  }

  def compareByName = {
    val personA = Person(Name("Luis", "Bicente"), Male, DateOfBirth(LocalDate.of(1979, 2, 16)))
    val personB = Person(Name("Luis", "Vicente"), Male, DateOfBirth(LocalDate.of(1979, 2, 16)))
    Ordering[Person].compare(personA, personB) must_== -1
  }

  def notValidPersonString = {
    val string: String = "Luis Vicente, Male"
    val gender: Option[Person] = Person.unapply(string)
    gender must beNone
  }

  def validParsonString = {
    val string: String = "Luis Vicente, Male, 16/02/79"
    val gender: Option[Person] = Person.unapply(string)
    gender must beSome(Person(Name("Luis", "Vicente"), Male, DateOfBirth(LocalDate.of(1979, 2, 16))))
  }
}
