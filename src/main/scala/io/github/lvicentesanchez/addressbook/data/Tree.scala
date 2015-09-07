package io.github.lvicentesanchez.addressbook.data

sealed trait Tree[+A]

case object Leaf extends Tree[Nothing]

case class Branch[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  def add[A](element: A, tree: Tree[A])(implicit O: Ordering[A]): Tree[A] = tree match {
    case Leaf => Branch(element, Leaf, Leaf)
    case Branch(value, left, right) if O.compare(element, value) == -1 => Branch(value, add(element, left), right)
    case Branch(value, left, right) if O.compare(element, value) == 1 => Branch(value, left, add(element, right))
    case other => other
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
      case Nil => acc
      case Leaf :: tail => go(acc, tail.asInstanceOf[List[Tree[A]]])
      case head :: tail =>
        val headCasted = head.asInstanceOf[Branch[A]]
        val tailCasted = tail.asInstanceOf[List[Tree[A]]]
        go(headCasted.value :: acc, headCasted.right :: headCasted.left :: tailCasted)
    }
    go(List(), List(tree))
  }

  def findFirst[A](f: A => Boolean, tree: Tree[A]): Option[A] = tree match {
    case Leaf => None
    case branch: Branch[A] if f(branch.value) => Some(branch.value)
    case branch: Branch[A] => findFirst(f, branch.left) orElse findFirst(f, branch.right)
  }
}
