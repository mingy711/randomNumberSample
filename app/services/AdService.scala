package services

import database.dao.AdDao
import models.ad.{DbAd, AdNotExistsException, Ad}
import models.random.{ RandomException, RandomManager }
import play.api.Logger
import play.api.libs.json.Json
import scaldi.{ Injectable, Injector }
import scalaz.{ \/- => R, -\/ => L, \/ => LR }
import contexts.PlatformContext.adServerContext

import models.ad.DbAdSerializers._
import models.ad.AdSerializers._

import scala.concurrent.Future

import org.apache.log4j.LogManager

/**
 * Created by mingyeung on 8/4/15.
 */
class AdService(implicit inj: Injector) extends Injectable {
  val logger = LogManager.getLogger("AdService")

  val adDao = inject[AdDao]

  def createLogAd(ad: Ad, client_timestamp: String): Ad = {
    ad.copy(
      sessionId = Some(java.util.UUID.randomUUID().toString()),
      client_timestamp = Some(client_timestamp)
    )
  }

  def getAd(appId: String, client_timestamp: String): Future[Ad] = {
/* if db is very slow, we can do it this wait to make the task more parallel
    val futureAd: Future[Option[DbAd]] = adDao.getAd(appId)
    val futFirstRandom = RandomManager.getRandom()
    val futSecondRandom = RandomManager.getRandom()

    for {
      optAd <- futureAd
      firstRandom <- futFirstRandom
      secondRandom <- futSecondRandom
    } yield {

    }
*/



    adDao.getAd(appId).flatMap { optDbAd =>
      optDbAd.map {
        dbAd =>
          {
            val futFirstRandom = RandomManager.getRandom()
            val futSecondRandom = RandomManager.getRandom()

            val futRandomResults: Future[Seq[LR[RandomException, Int]]] = Future.sequence(Seq(futFirstRandom, futSecondRandom))
            futRandomResults.map {
              randomResults: Seq[LR[RandomException, Int]] =>
                {
                  val randoms: Seq[Int] = randomResults.map(_.toOption).flatten
                  val exceptions = randomResults.map(_.swap.toOption).flatten

                  val ad: Ad = Ad(dbAd.name, if (randoms.size == randomResults.size) Some(randoms) else None, None, None)
                  val logAd: Ad = createLogAd(ad, client_timestamp)
                  logger.debug(Json.stringify(Json.toJson(logAd)))

                  ad

                  /*

                  if (randoms.size == randomResults.size) {
                    //val ad: Ad = Ad(dbAd.name, Some(randoms), None, None)
                    //val logAd: Ad = createLogAd(ad, client_timestamp)
                    logger.debug(Json.stringify(Json.toJson(logAd)))

                    Future.successful(ad)
                  } else {
                    //val exceptions = randomResults.map(_.swap.toOption).flatten

                    //val ad: Ad = Ad(dbAd.name, None, None, None)
                    //val logAd: Ad = createLogAd(ad, client_timestamp)
                    logger.debug(Json.stringify(Json.toJson(logAd)))

                    Future.successful(ad)
                  }
                  */
                }
            }
          }
      }.getOrElse {
        val ad: Ad = Ad("", None, None, None)
        val logAd: Ad = createLogAd(ad, client_timestamp)
        logger.debug(Json.stringify(Json.toJson(logAd)))

        Future.successful(ad)
      }
    }
  }

}
