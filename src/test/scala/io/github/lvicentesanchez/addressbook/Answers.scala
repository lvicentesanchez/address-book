package io.github.lvicentesanchez.addressbook

import org.specs2.Spec

class Answers extends Spec {
  def is = s2"""
    The answer to the questions in the Task is:

      How many males are in the address book? 3                            $answerHowManyMales
      Who is the oldest person in the address book? Wes Jackson (14/08/74) $answerOldestPerson
      How many days older is Bill than Paul? 2863                          $answerHowMayDaysBetweenBillAndPaul
  """

  def answerHowManyMales = {
    false
  }

  def answerOldestPerson = {
    false
  }

  def answerHowMayDaysBetweenBillAndPaul = {
    false
  }
}
