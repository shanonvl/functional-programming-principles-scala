// Lecture 3.1
object overrides {
  println("Welcome to Scala worksheets")
  new Sub().foo
}

abstract class Base {
  def foo = 1
  def bar: Int
}

class Sub extends Base {
  override def foo = 2
  def bar = 3
}
