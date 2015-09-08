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

    def balanceLeft(color: Color, z: A, l: Tree[A], d: Tree[A]): Tree[A] = l match {

      case Branch(y, Red, Branch(x, Red, a, b), c) =>
        Branch(y, Red, Branch(x, Black, a, b), Branch(z, Black, c, d))

      case Branch(x, Red, a, Branch(y, Red, b, c)) =>
        Branch(y, Red, Branch(x, Black, a, b), Branch(z, Black, c, d))

      case _ => Branch(z, color, l, d)
    }

    def balanceRight(color: Color, x: A, a: Tree[A], r: Tree[A]): Tree[A] = r match {

      case Branch(z, Red, Branch(y, Red, b, c), d) =>
        Branch(y, Red, Branch(x, Black, a, b), Branch(z, Black, c, d))

      case Branch(y, Red, b, Branch(z, Red, c, d)) =>
        Branch(y, Red, Branch(x, Black, a, b), Branch(z, Black, c, d))

      case _ => Branch(x, color, a, r)
    }

    balanceAdd(element, tree) match {

      case Leaf => Leaf
      case Branch(value, _, left, right) => Branch(value, Black, left, right)
    }
  }

  def height[A](tree: Tree[A]): Int = tree match {
    case Leaf => 0
    case Branch(_, _, left, right) => 1 + math.max(height(left), height(right))
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

    def go(rest: Tree[A]): List[A] = rest match {

      case Leaf =>
        Nil

      case branch: Branch[A] =>
        go(branch.left) ++ List(branch.value) ++ go(branch.right)
    }

    go(tree)
  }

  def findFirst[A](f: A => Boolean, tree: Tree[A]): Option[A] = tree match {

    case Leaf => None
    case branch: Branch[A] if f(branch.value) => Some(branch.value)
    case branch: Branch[A] => findFirst(f, branch.left) orElse findFirst(f, branch.right)
  }
}
