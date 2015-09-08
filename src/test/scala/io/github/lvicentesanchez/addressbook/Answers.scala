package io.github.lvicentesanchez.addressbook

import io.github.lvicentesanchez.addressbook.data.{ Leaf, Tree }
import io.github.lvicentesanchez.addressbook.data.syntax.tree._
import io.github.lvicentesanchez.addressbook.model._
import java.time.temporal.{ ChronoUnit, Temporal }
import java.time.LocalDate
import org.specs2.Spec
import org.specs2.execute.{ AsResult, Result }
import org.specs2.specification.ForEach
import scala.io.Source

class Answers extends Spec with ForEach[Tree[Person]] {

  override protected def foreach[R](f: (Tree[Person]) => R)(implicit AR: AsResult[R]): Result = {

    val stream = this.getClass.getResourceAsStream("/AddressBook")
    val source = Source.fromInputStream(stream)
    val input =
      source.
        getLines().
        flatMap(Person.unapply(_)).
        foldLeft[Tree[Person]](Leaf)(_.add(_))
    val result = f(input)

    source.close()
    stream.close()

    AR.asResult(result)
  }

  def is = s2"""

    The answers to the questions are:

      1. How many males are in the address book? 3                            ${answerHowManyMales _}
      2. Who is the oldest person in the address book? Wes Jackson (14/08/74) ${answerOldestPerson _}
      3. How many days older is Bill than Paul? 2862                          ${answerHowMayDaysBetweenBillAndPaul _}
  """

  def answerHowManyMales(input: Tree[Person]) = {

    val answer = howManyByCondition(input, isMale)
    val expected = 3

    answer must_== expected
  }

  def answerHowMayDaysBetweenBillAndPaul(input: Tree[Person]) = {

    val answer = for {
      bill <- input.findFirst(_.name.first.toUpperCase == "BILL")
      paul <- input.findFirst(_.name.first.toUpperCase == "PAUL")
    } yield daysBetween(bill.dob.date, paul.dob.date)
    val expected = 2862L

    answer must beSome(expected)
  }

  def answerOldestPerson(input: Tree[Person]) = {

    val answer = input.min
    val expected = Person(Name("Wes", "Jackson"), Male, DateOfBirth(LocalDate.of(1974, 8, 14)))

    answer must beSome(expected)
  }

  def daysBetween(startInclusive: Temporal, endExclusive: Temporal): Long =
    ChronoUnit.DAYS.between(startInclusive, endExclusive)

  def howManyByCondition(input: Tree[Person], f: Person => Boolean): Int =
    input.fold(0)((acc, a) => if (f(a)) acc + 1 else acc)

  val isMale: Person => Boolean = _.gender == Male
}
