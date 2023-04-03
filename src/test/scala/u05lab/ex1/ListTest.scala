package u05lab.ex1

import org.junit.Test
import org.junit.Assert.*

class ListTest:

  /** todo */
  @Test
  def testZipRight(): Unit =
    val l = List(1, 2, 3, 4, 5)
   // assertEquals(List((1, 0), (2, 1), (3, 2), (4, 3), (5, 4)), l.zipRight)

  @Test
  def testPartition(): Unit =
    val l = List(1, 2, 3, 4, 5)
    val (even, odd) = l.partition(_ % 2 == 0)
    assertEquals(List(2, 4), even)
    assertEquals(List(1, 3, 5), odd)

