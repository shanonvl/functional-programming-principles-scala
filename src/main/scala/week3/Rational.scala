package week3

/**
 * Lecture 3.2
 */
class Rational(x: Int, y: Int) {

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  private def g = gcd(x,y)

  def numer = x / g
  def denom = y / g

  def add(that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def sub(that: Rational) = add(that.neg)

  def neg = new Rational(-numer, denom)

  override def toString = numer + "/" + denom

}