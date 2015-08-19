package database.dao.impl

import database.dao.AdDao
import database.exceptions.DataInsertException
import models.ad.DbAd
import models.ad.DbAdSerializers._
import play.api.Play
import play.api.cache.Cache
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.DefaultDB
import reactivemongo.bson.BSONObjectID
import play.api.libs.json.{ Reads, Writes, Json, JsObject }
import scaldi.{ Injectable, Injector }
import play.api.Play.current

import scala.reflect.ClassTag

/* Implicits */
import play.modules.reactivemongo.json.BSONFormats._

import scala.concurrent.{ Future, ExecutionContext }
import scalaz.{ \/ => LR, -\/ => L, \/- => R }

/**
 * Created by mingyeung on 8/4/15.
 */
object AdDaoImpl {
  val cacheTimeoutDurationSec = Play.configuration.getInt("cache.timeout.durationSec").getOrElse(300)

  def getCache[T: ClassTag](key: String, f: () => T, isReset: Boolean = false): (Boolean, T) = {
    if (isReset) {
      val result: T = f()
      Cache.set(key, result, AdDaoImpl.cacheTimeoutDurationSec)
      (false, result)
    } else {
      val maybeItem: Option[T] = Cache.getAs[T](key)
      maybeItem match {
        case Some(item) =>
          (true, item)
        case None =>
          getCache(key, f, true)
      }
    }
  }
}

class AdDaoImpl(val collectionName: String = "Ad", val jsonCollection: JSONCollection, val db: DefaultDB)(implicit inj: Injector) extends AdDao with Injectable {

  def getAd(adId: String, useCache: Boolean = true)(implicit e: ExecutionContext, reader: Reads[DbAd]): Future[Option[DbAd]] = {
    if (useCache) {
        def fun() = { getAd(adId, false) }
      val cacheResult: (Boolean, Future[Option[DbAd]]) = AdDaoImpl.getCache(adId, fun)
      cacheResult._2
    } else {
      try {
        jsonCollection.find(Json.obj("_id" -> BSONObjectID(adId))).one[DbAd]
      } catch {
        case e: NoSuchElementException =>
          Future.successful(None)
        case e: Exception =>
          Future.successful(None)
      }
    }
  }

  def insert(dbAd: DbAd)(implicit e: ExecutionContext): Future[LR[Exception, DbAd]] = {
    jsonCollection.insert(dbAd).flatMap {
      lastError =>
        {
          if (lastError.ok) Future.successful(R(dbAd)) else Future.successful(L(DataInsertException()))
        }
    }
  }
}
