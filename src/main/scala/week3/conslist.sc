import java.util.NoSuchElementException
object conslist {
  def nth[T](n: Int, l: List[T]): T = {
    if (l.isEmpty) throw new IndexOutOfBoundsException()
    if (n == 0) l.head
    else nth(n-1,l.tail)
  }
  val l1 = new Cons(3,new Cons(4,new Cons(5,new Nil)))
  nth(0,l1)
  nth(1,l1)
  nth(2,l1)
  nth(5,l1)
}
trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}
class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}
