package ex1

import org.junit.Test
import org.junit.Assert.*
import ex1.*
import ex1.List.*

class ListTest:

  /** todo */
  @Test
  def testZipRight(): Unit =
    val l = List(1, 2, 3, 4, 5)
    assertEquals(List((1, 0), (2, 1), (3, 2), (4, 3), (5, 4)), l.zipRight)


  @Test
  def testPartition(): Unit =
    val l = List(1, 2, 3, 4, 5)
    val (even, odd) = l.partition(_ % 2 == 0)
    assertEquals(List(2, 4), even)
    assertEquals(List(1, 3, 5), odd)

  // similar to partition, but here the predicate creates a split point
  @Test
  def testSpan(): Unit =
    val l = List(1, 2, 3, 4, 5)
    val (less, more) = l.span(_ < 3)
    assertEquals(List(3, 4, 5), more)
    assertEquals(List(1, 2), less)

  @Test
  def testTakeRight(): Unit =
    val l = List(1, 2, 3, 4, 5)
    assertEquals(List(3, 4, 5), l.takeRight(3))
    assertEquals(List(1, 2, 3, 4, 5), l.takeRight(5))
    assertEquals(List(1, 2, 3, 4, 5), l.takeRight(6))
    assertEquals(List(), l.takeRight(0))
    assertEquals(List(), l.takeRight(-1))



