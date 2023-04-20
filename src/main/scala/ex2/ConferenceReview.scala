package ex2

import a03b.sol1.Pair

import scala.::

trait ConferenceReview:

  import ConferenceReview.Question

  def loadReview(article: Int, scores: Map[Question, Int]): Unit

  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit

  def orderedScores(article: Int, question: Question): List[Int]

  def averageFinalScore(article: Int): Double

  def acceptedArticles(): Set[Int]

  def sortedAcceptedArticles(): List[(Int, Double)]

  def averageWeightedFinalScoreMap(): Map[Int, Double]

  def getScores(): List[(Int, Map[Question, Int])]

object ConferenceReview:
  enum Question:
    case RELEVANCE
    case SIGNIFICANCE
    case CONFIDENCE
    case FINAL

  def apply(): ConferenceReview = ConferenceReviewImpl()

  private class ConferenceReviewImpl extends ConferenceReview:
    private var reviews: List[(Int, Map[Question, Int])] = List()

    override def loadReview(article: Int, scores: Map[Question, Int]): Unit =
      this.reviews = this.reviews :+ (article, scores)

    override def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit =
      this.reviews = this.reviews :+
        (
          article, Map(
          Question.RELEVANCE -> relevance,
          Question.SIGNIFICANCE -> significance,
          Question.CONFIDENCE -> confidence,
          Question.FINAL -> fin
        )
        )


    override def orderedScores(article: Int, question: Question): List[Int] =
      this.reviews
        .filter(_._1 == article)
        .map((_, r) => r)
        .map(map => map(question))
        .sorted
        .reverse


    override def averageFinalScore(article: Int): Double =
      val articleReviews = this.orderedScores(article, Question.FINAL)
      articleReviews.foldLeft(0.0)((e, s) => e + s) / articleReviews.length

    override def acceptedArticles(): Set[Int] =
      this.reviews.filter((a, r) => this.averageFinalScore(a) >= 8)
        .map((a, _) => a)
        .toSet

    override def sortedAcceptedArticles(): List[(Int, Double)] = ???

    override def getScores(): List[(Int, Map[Question, Int])] = this.reviews

    override def averageWeightedFinalScoreMap(): Map[Int, Double] = ???

