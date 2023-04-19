package ex2

import ConferenceReview.Question
import org.junit.Test
import org.junit.Assert.*

class ConferenceReviewTest:
  val reviews = ConferenceReview()
  reviews.loadReview(1, 10, 10, 10, 8)
  reviews.loadReview(2, 10, 10, 10, 10)
  reviews.loadReview(3, 10, 10, 10, 9)
  reviews.loadReview(4, 10, 10, 10, 7)
  reviews.loadReview(5, 10, 10, 10, 6)
  reviews.loadReview(1, 10, 10, 10, 5)
  println(reviews.getScores())

  @Test def testOrderedScores(): Unit =
    val expected = List(8, 5)
    val actual = reviews.orderedScores(1, Question.FINAL)
    assertEquals(expected, actual)

