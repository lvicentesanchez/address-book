package io.github.lvicentesanchez.addressbook.data

import io.github.lvicentesanchez.addressbook.data.syntax.tree._
import org.specs2.Spec

class TreeSpec extends Spec {

  def is = s2"""

    add should

      create a balanced tree when adding all elements from a sorted list (ascending order)  $balancedFromSorterListAsc
      create a balanced tree when adding all elements from a sorted list (descending order) $balancedFromSorterListDesc

    max should

      return None, if tree is empty                                                         $maxIfEmpty
      return Some(lowest), if tree is not empty                                             $maxIfNotEmpty

    min should

      return None, if tree is empty                                                         $minIfEmpty
      return Some(greatest), if tree is not empty                                           $minIfNotEmpty

    values should

      return tree elements in order                                                         $valuesInOrder
  """

  def balancedFromSorterListAsc = {

    val input: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val tree: Tree[Int] = input.foldLeft[Tree[Int]](Leaf)(_.add(_))
    val depth: Long = tree.height
    val maxDepth: Long = 2 * math.round(math.ceil(math.log(input.size)))

    depth must be_<=(maxDepth)
  }

  def balancedFromSorterListDesc = {

    val input: List[Int] = List(9, 8, 7, 6, 5, 4, 3, 2, 1)
    val tree: Tree[Int] = input.foldLeft[Tree[Int]](Leaf)(_.add(_))
    val depth: Long = tree.height
    val maxDepth: Long = 2 * math.round(math.ceil(math.log(input.size)))

    depth must be_<=(maxDepth)
  }

  def maxIfEmpty = {

    val input: List[Int] = List()
    val tree: Tree[Int] = input.foldLeft[Tree[Int]](Leaf)(_.add(_))
    val max: Option[Int] = tree.max

    max must beNone
  }

  def maxIfNotEmpty = {

    val input: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val tree: Tree[Int] = input.foldLeft[Tree[Int]](Leaf)(_.add(_))
    val max: Option[Int] = tree.max
    val expected: Int = 9

    max must beSome(expected)
  }

  def minIfEmpty = {

    val input: List[Int] = List()
    val tree: Tree[Int] = input.foldLeft[Tree[Int]](Leaf)(_.add(_))
    val max: Option[Int] = tree.min

    max must beNone
  }

  def minIfNotEmpty = {

    val input: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val tree: Tree[Int] = input.foldLeft[Tree[Int]](Leaf)(_.add(_))
    val max: Option[Int] = tree.min
    val expected: Int = 1

    max must beSome(expected)
  }

  def valuesInOrder = {

    val input: List[Int] = List(9, 8, 7, 6, 5, 4, 3, 2, 1)
    val tree: Tree[Int] = input.foldLeft[Tree[Int]](Leaf)(_.add(_))
    val values: List[Int] = tree.values
    val expected: List[Int] = input.sorted

    values must_== expected
  }
}
