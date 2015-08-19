import modules._
import org.apache.log4j.LogManager

import play.api._

import play.api.mvc.{Result, RequestHeader}
import scaldi.play.ScaldiSupport

import scala.concurrent._
import scala.util.control.NonFatal

//import org.apache.logging.log4j.Logger
//import org.apache.logging.log4j.LogManager
//import org.apache.logging.slf4j
import play.api.Play.current


object Global extends GlobalSettings with ScaldiSupport {
  val logger = LogManager.getLogger("Global")

  override def applicationModule =
      new AdModule ::
      new WebModule

  override def onStart(app: Application) {
    //org.apache.logging.log4j.core.config.XMLConfiguration


    super.onStart(app)

    logger.info("Application started")
  }

  override def onStop(app: Application) {
    super.onStop(app)
    logger.info("Application shutdown")
  }

  override def onError(request: RequestHeader, ex: Throwable): Future[Result] = {
    ex match {
      case NonFatal(e) => {
        logger.error("Non Fatal Exception :-  Cause :- "+e.getMessage, e)
        Future.successful(play.api.mvc.Results.InternalServerError)
      }

      case e:Exception => {
        logger.error("Request Header Info :- "+request.toString())
        logger.error("Fatal Exception :- Cause :- "+e.getMessage, e)
        // let this fatal exception bubble. !!!!! Don't catch it
        throw e
      }
    }
  }
}