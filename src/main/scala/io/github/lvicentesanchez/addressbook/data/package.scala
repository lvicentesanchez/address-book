package io.github.lvicentesanchez.addressbook

package object data {

  object tree {

    implicit class TreeSyntax[A](val tree: Tree[A]) extends AnyVal {

      def add(element: A)(implicit O: Ordering[A]): Tree[A] = Tree.add(element, tree)

      def findFirst(f: A => Boolean): Option[A] = Tree.findFirst(f, tree)

      def fold[B](value: B)(f: (B, A) => B): B = Tree.fold(value, tree)(f)

      def max: Option[A] = Tree.max(tree)

      def min: Option[A] = Tree.min(tree)
    }
  }
}
