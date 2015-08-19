package models.random

import play.api.Play
import play.api.libs.ws.WS
import scalaz.{ \/- => R, -\/ => L, \/ => LR }
import scala.concurrent.Future
import contexts.PlatformContext.randomServiceContext
import play.api.Play.current

/**
 * Created by mingyeung on 8/4/15.
 */
object RandomManager {
  val randomUrl = Play.configuration.getString("randomGeneratorServiceUrl").getOrElse("")

  def getRandom(): Future[LR[RandomException, Int]] = {
    val responseFuture = WS.url(randomUrl).get()


    responseFuture.map { response =>
      response.status match {
        case 200 => {
          val randomNumber: Int = response.body.trim().toInt
          R(randomNumber)
        }
        case _ =>
          L(RandomServiceException())
      }
    } recover {
      case e: Exception =>
        L(RandomServiceException())
    }




    /*
    responseFuture.flatMap { response =>
      response.status match {
        case 200 =>
          try {
            val randomNumber: Int = response.body.trim().toInt
            Future.successful(R(randomNumber))
          } catch {
            case e: Exception =>
              Future.successful(L(DoesNotReceiveRandomIntegerException()))
          }

        case _ =>
          Future.successful(L(RandomServiceException()))
      }
    }
    */
  }
}
