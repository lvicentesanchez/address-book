package io.github.lvicentesanchez.addressbook.model

import org.specs2.Spec

class GenderSpec extends Spec {
  def is = s2"""
    Gender should

      return None if string doesn't contain the word 'female', with any combination of uppercase/lowercase letters   $notValidFemaleString
      return Some(Female) if string contains the word 'female', with any combination of uppercase/lowercase letters' $validFemaleString

      return None if string doesn't contain the word 'male', with any combination of uppercase/lowercase letters     $notValidMaleString
      return Some(Male) if string contains the word 'male', with any combination of uppercase/lowercase letters'     $validMaleString
  """

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
