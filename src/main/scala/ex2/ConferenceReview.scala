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


    private def calculateAverage(scores: List[Int]): Double =
      scores.foldLeft(0.0)((e, s) => e + s) / scores.length

    override def averageFinalScore(article: Int): Double =
      this.calculateAverage(this.orderedScores(article, Question.FINAL))

    override def acceptedArticles(): Set[Int] =
      this.reviews
        .filter((a, _) => this.averageFinalScore(a) >= 8)
        .map((a, _) => a)
        .toSet


    override def sortedAcceptedArticles(): List[(Int, Double)] =
      this.acceptedArticles()
        .map(a => (a, averageFinalScore(a)))
        .toList
        .sorted
        .reverse


    override def getScores(): List[(Int, Map[Question, Int])] = this.reviews

    private def calculateWeightedAverage(a: Int): Double =
      val confidenceAvg = this.calculateAverage(this.orderedScores(a, Question.CONFIDENCE))
      val finalAvg = this.calculateAverage(this.orderedScores(a, Question.FINAL))
      (confidenceAvg * finalAvg) / 10


    override def averageWeightedFinalScoreMap(): Map[Int, Double] =
      this.reviews
        .map((a, _) => (a, this.calculateWeightedAverage(a)))
        .sorted
        .toMap




