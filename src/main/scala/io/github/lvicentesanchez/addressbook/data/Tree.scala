package io.github.lvicentesanchez.addressbook.data

/**
 * Red-Black Tree implementation inspired by:
 *   https://github.com/vkostyukov/scalacaster/blob/master/src/tree/RBTree.scala
 */

sealed trait Color

case object Red extends Color
case object Black extends Color

sealed trait Tree[+A]

case object Leaf extends Tree[Nothing]

case class Branch[A](value: A, color: Color, left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  def add[A: Ordering](element: A, tree: Tree[A])(implicit O: Ordering[A]): Tree[A] = {

    def balanceAdd(x: A, rest: Tree[A]): Tree[A] = rest match {

      case Leaf =>
        Branch(x, Red, Leaf, Leaf)

      case Branch(value, color, left, right) if O.compare(element, value) < 0 =>
        balanceLeft(color, value, balanceAdd(x, left), right)

      case Branch(value, color, left, right) if O.compare(element, value) > 0 =>
        balanceRight(color, value, left, balanceAdd(x, right))

      case other => other
    }

    def balanceLeft(color: Color, x: A, left: Tree[A], right: Tree[A]): Tree[A] = (color, left, right) match {

      case (Black, Branch(y, Red, Branch(z, Red, lll, llr), lr), r) =>
        Branch(y, Red, Branch(z, Black, lll, llr), Branch(x, Black, lr, r))

      case (Black, Branch(z, Red, ll, Branch(y, Red, lrl, lrr)), r) =>
        Branch(y, Red, Branch(z, Black, ll, lrl), Branch(x, Black, lrr, r))

      case _ => Branch(x, color, left, right)
    }

    def balanceRight(color: Color, x: A, left: Tree[A], right: Tree[A]): Tree[A] = (color, left, right) match {

      case (Black, l, Branch(y, Red, rl, Branch(z, Red, rrl, rrr))) =>
        Branch(y, Red, Branch(x, Black, l, rl), Branch(z, Black, rrl, rrr))

      case (Black, l, Branch(z, Red, Branch(y, Red, rll, rlr), rr)) =>
        Branch(y, Red, Branch(x, Black, l, rll), Branch(z, Black, rlr, rr))

      case _ => Branch(x, color, left, right)
    }

    balanceAdd(element, tree) match {

      case Leaf => Leaf
      case Branch(value, _, left, right) => Branch(value, Black, left, right)
    }
  }

  def fold[A, B](value: A, tree: Tree[B])(f: (A, B) => A): A =
    values(tree).foldLeft(value)(f)

  @annotation.tailrec
  def max[A](tree: Tree[A]): Option[A] = tree match {

    case Leaf => None
    case branch: Branch[A] if branch.right == Leaf => Some(branch.value)
    case branch: Branch[A] => max(branch.right)
  }

  @annotation.tailrec
  def min[A](tree: Tree[A]): Option[A] = tree match {

    case Leaf => None
    case branch: Branch[A] if branch.left == Leaf => Some(branch.value)
    case branch: Branch[A] => min(branch.left)
  }

  def values[A](tree: Tree[A]): List[A] = {

    @annotation.tailrec
    def go(acc: List[A], rest: List[Tree[A]]): List[A] = rest match {

      case Leaf :: tail =>
        go(acc, tail.asInstanceOf[List[Tree[A]]])

      case head :: tail =>
        val headCasted = head.asInstanceOf[Branch[A]]
        val tailCasted = tail.asInstanceOf[List[Tree[A]]]
        go(headCasted.value :: acc, headCasted.right :: headCasted.left :: tailCasted)

      case Nil => acc
    }

    go(List(), List(tree))
  }

  def findFirst[A](f: A => Boolean, tree: Tree[A]): Option[A] = tree match {

    case Leaf => None
    case branch: Branch[A] if f(branch.value) => Some(branch.value)
    case branch: Branch[A] => findFirst(f, branch.left) orElse findFirst(f, branch.right)
  }
}
