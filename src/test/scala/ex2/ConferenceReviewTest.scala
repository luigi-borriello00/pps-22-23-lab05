package ex2

import ConferenceReview.Question
import org.junit.{Before, Test}
import org.junit.Assert.*

class ConferenceReviewTest:
  val reviews = ConferenceReview()

  @Before def setup(): Unit =
    reviews.loadReview(1, 10, 10, 10, 8)
    reviews.loadReview(2, 10, 10, 10, 10)
    reviews.loadReview(3, 10, 10, 10, 9)
    reviews.loadReview(3, 10, 10, 10, 9)
    reviews.loadReview(4, 10, 10, 10, 7)
    reviews.loadReview(5, 10, 10, 10, 6)
    reviews.loadReview(1, 10, 10, 10, 5)

  @Test def testOrderedScores(): Unit =
    val expected = List(8, 5)
    val actual = reviews.orderedScores(1, Question.FINAL)
    assertEquals(expected, actual)

  @Test def testAverageFinalScore(): Unit =
    val expected: Double = 6.5
    val actual = reviews.averageFinalScore(1)
    assertEquals(expected, actual, 0.0001)

  @Test def testAcceptedArticles(): Unit =
    val expected = Set(2, 3)
    val actual = reviews.acceptedArticles()
    assertEquals(expected, actual)

  @Test def testSortedAcceptedArticles() =
    val expected = List((3, 9.0), (2, 10.0))
    val actual = reviews.sortedAcceptedArticles()
    assertEquals(expected, actual)

  // the average value of CONFIDENCE*FINAL/10
  @Test def testWeightedAverageFinalScore() =
    val expected = Map(1 -> 6.5, 2 -> 10.0, 3 -> 9.0, 4 -> 7.0, 5 -> 6.0)
    assertEquals(expected, reviews.averageWeightedFinalScoreMap())
