package io.github.lvicentesanchez.addressbook.model

import org.specs2.Spec

class GenderSpec extends Spec {

  def is = s2"""

    ordering should
    
      return > 0 if genderA < genderB (lexicographical order)                                                       $lowerThan
      return = 0 if genderA = genderB (lexicographical order)                                                       $equalTo
      return < 0 if genderA > genderB (lexicographical order)                                                       $greaterThan
           
    unapply should

      return None if string doesn't contain the word 'female', with any combination of uppercase/lowercase letters  $notValidFemaleString
      return Some(Female) if string contains the word 'female', with any combination of uppercase/lowercase letters $validFemaleString

      return None if string doesn't contain the word 'male', with any combination of uppercase/lowercase letters    $notValidMaleString
      return Some(Male) if string contains the word 'male', with any combination of uppercase/lowercase letters     $validMaleString
  """

  def equalTo = {

    val genderA: Gender = Female
    val genderB: Gender = Female

    Ordering[Gender].compare(genderA, genderB) must_== 0
  }

  def greaterThan = {

    val genderA: Gender = Male
    val genderB: Gender = Female

    Ordering[Gender].compare(genderA, genderB) must be_>(0)
  }

  def lowerThan = {

    val genderA: Gender = Female
    val genderB: Gender = Male

    Ordering[Gender].compare(genderA, genderB) must be_<(0)
  }

  def notValidFemaleString = {

    val string: String = "orange"
    val gender: Option[Gender] = Gender.unapply(string)

    gender must beNone
  }

  def notValidMaleString = {

    val string: String = "orange"
    val gender: Option[Gender] = Gender.unapply(string)

    gender must beNone
  }

  def validFemaleString = {

    val string: String = "fEmAlE"
    val gender: Option[Gender] = Gender.unapply(string)

    gender must beSome[Gender](Female)
  }

  def validMaleString = {

    val string: String = "MaLe"
    val gender: Option[Gender] = Gender.unapply(string)

    gender must beSome[Gender](Male)
  }
}
