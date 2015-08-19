package models.ad

/**
 * Created by mingyeung on 8/4/15.
 */
class AdException(messageIn: Option[String] = None,
                  causeIn: Option[Throwable] = None) extends Exception(messageIn.getOrElse(null), causeIn.getOrElse(null))

case class AdNotExistsException(messageIn: Option[String] = None,
                                causeIn: Option[Throwable] = None) extends AdException(messageIn, causeIn)