package io.github.lvicentesanchez.addressbook

import io.github.lvicentesanchez.addressbook.model.Person
import org.specs2.Spec
import org.specs2.execute.{ Result, AsResult }
import org.specs2.specification.ForEach

import scala.io.Source

class Answers extends Spec with ForEach[Iterator[Person]] {

  override protected def foreach[R](f: (Iterator[Person]) => R)(implicit AR: AsResult[R]): Result = {
    val stream = this.getClass.getResourceAsStream("/AddressBook")
    val source = Source.fromInputStream(stream)
    val input = source.getLines().flatMap(Person.unapply(_))
    val result = f(input)
    source.close()
    stream.close()
    AR.asResult(result)
  }

  def is = s2"""
    The answer to the questions in the Task is:

      How many males are in the address book? 3                            ${answerHowManyMales _}
      Who is the oldest person in the address book? Wes Jackson (14/08/74) ${answerOldestPerson _}
      How many days older is Bill than Paul? 2863                          ${answerHowMayDaysBetweenBillAndPaul _}
  """

  def answerHowManyMales(input: Iterator[Person]) = {
    input.foreach(println)
    false
  }

  def answerOldestPerson(input: Iterator[Person]) = {
    false
  }

  def answerHowMayDaysBetweenBillAndPaul(input: Iterator[Person]) = {
    false
  }
}
