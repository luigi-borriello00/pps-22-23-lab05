package ex2

import a03b.sol1.Pair

import scala.::

trait ConferenceReview:
  enum Question:
    case RELEVANCE
    case SIGNIFICANCE
    case CONFIDENCE
    case FINAL

  def loadReview(article: Int, scores: Map[Question, Int]): Unit

  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit

  def orderedScores(article: Int, question: Question): List[Int]

  def averageFinalScore(article: Int): Double

  def acceptedArticles(): Set[Int]

  def sortedAcceptedArticles(): List[(Int, Double)]

  def averageWeightedFinalScoreMap(): Map[Int, Double]

object ConferenceReview:
  def apply(): ConferenceReview = ConferenceReviewImpl()

  private class ConferenceReviewImpl extends ConferenceReview:
    private val reviews: List[(Int, Map[Question, Int])] = Nil

    override def loadReview(article: Int, scores: Map[Question, Int]): Unit =
      this.reviews :+ (article, scores)

    override def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit =
      this.reviews :+
        (
          article, Map(
          Question.RELEVANCE -> relevance,
          Question.SIGNIFICANCE -> significance,
          Question.CONFIDENCE -> confidence,
          Question.FINAL -> fin
        )
        )

    override def orderedScores(article: Int, question: Question): List[Int] =
      ???

    override def averageFinalScore(article: Int): Double = ???

    override def acceptedArticles(): Set[Int] = ???

    override def sortedAcceptedArticles(): List[Pair[Int, Double]] = ???

    override def averageWeightedFinalScoreMap(): Map[Int, Double] = ???

