package funsets

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 * - run the "test" command in the SBT console
 * - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   * - test
   * - ignore
   * - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }


  import funsets.FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   * val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains common elements") {
    new TestSets {
      val s12 = union(s1, s2)
      val s23 = union(s2, s3)
      assert(contains(intersect(s12, s23), 2), "Intersect 2")
      assert(!contains(intersect(s12, s23), 1), "Intersect 1")
    }
  }

  test("diffs contain uncommon elements") {
    new TestSets {
      val s12 = union(s1, s2)
      val s23 = union(s2, s3)
      var diffs = diff(s12, s23)
      assert(contains(diffs, 1) && contains(diffs, 3), "Diff 1")
      assert(!contains(diffs, 2), "Diff 2")
    }
  }

  test("filters satisfy passed function") {
    new TestSets {
      val s12 = union(s1, s2)
      val alls = union(s12, s3)
      val lessThan3 = filter(alls, x => x < 3)
      val greaterThan2 = filter(alls, x => x > 2)
      assert(contains(lessThan3, 1) && contains(lessThan3, 2) && !contains(lessThan3, 3), "Filters 1")
      assert(contains(greaterThan2, 3) && !contains(greaterThan2, 2), "Filters 2")
    }
  }

  test("forall function satisfies all set members") {
    new TestSets {
      val s12 = union(s1, s2)
      val alls = union(s12, s3)

      assert(!forall(alls, x => x == 3), "Forall 1")
      assert(forall(alls, x => x < 4), "Forall 2")
      assert(forall(alls, x => x > 0), "Forall 3")
    }
  }

  test("exists function") {
    new TestSets {
      val s12 = union(s1, s2)
      val alls = union(s12, s3)

      assert(!exists(alls, x => x == 4), "Existss 1")
      assert(exists(alls, x => x == 1), "Exists 2")
    }
  }

  test("map function") {
    new TestSets {
      val s12 = union(s1, s2)
      val alls = union(s12, s3)

      val mapped = map(alls,x => x+1)

      assert(exists(mapped, x => x == 4), "Exists 1")
      assert(!exists(mapped, x => x == 1), "Exists 2")
    }
  }

}
