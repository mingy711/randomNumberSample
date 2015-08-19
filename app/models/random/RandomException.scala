package models.random

/**
 * Created by mingyeung on 8/4/15.
 */
class RandomException(messageIn: Option[String] = None,
                      causeIn: Option[Throwable] = None) extends Exception(messageIn.getOrElse(null), causeIn.getOrElse(null))

case class DoesNotReceiveRandomIntegerException(messageIn: Option[String] = None,
                                                causeIn: Option[Throwable] = None) extends RandomException(messageIn, causeIn)

case class RandomServiceException(messageIn: Option[String] = None,
                                  causeIn: Option[Throwable] = None) extends RandomException(messageIn, causeIn)