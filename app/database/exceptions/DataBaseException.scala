package database.exceptions

/**
 * Created by mingyeung on 8/5/15.
 */
class DataBaseException(messageIn: Option[String] = None,
                        causeIn: Option[Throwable] = None) extends Exception(messageIn.getOrElse(null), causeIn.getOrElse(null))

case class DataNotExistException(messageIn: Option[String] = None,
                                 causeIn: Option[Throwable] = None) extends DataBaseException(messageIn, causeIn)

case class DataInsertException(messageIn: Option[String] = None,
                               causeIn: Option[Throwable] = None) extends DataBaseException(messageIn, causeIn)