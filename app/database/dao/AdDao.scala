package database.dao

import models.ad.DbAd
import play.api.libs.json.Reads

import scala.concurrent.{ Future, ExecutionContext }
import scalaz.{ \/ => LR }

/**
 * Created by mingyeung on 8/4/15.
 */
trait AdDao {
  def getAd(adId: String, useCache: Boolean = true)(implicit e: ExecutionContext, reader: Reads[DbAd]): Future[Option[DbAd]]

  def insert(dbAd: DbAd)(implicit e: ExecutionContext): Future[LR[Exception, DbAd]]
}
