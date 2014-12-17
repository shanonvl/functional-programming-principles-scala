// Lecture 3.1

// object must be defined prior to usage ( this has to be *above* intsets object )
object Empty extends IntSet {
  def contains(x: Int) = false
  def incl(x: Int) = new NonEmpty(x, Empty, Empty)
  def union(other: IntSet) = other
  override def toString = "."
}

object intsets {
  println("Welcome to Scala worksheets")
  val t1 = new NonEmpty(3, Empty, Empty)
  val t2 = t1 incl 4 incl 2
}

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  def contains(x: Int): Boolean = {
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  }

  def incl(x: Int): IntSet = {

    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  }

  def union(other: IntSet) = {
    ((left union right) union other) incl elem
  }

  override def toString = "{" + left + elem + right + "}"

}