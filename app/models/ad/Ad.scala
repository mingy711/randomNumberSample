package models.ad

import play.api.libs.json.Json
import reactivemongo.bson.BSONObjectID
import play.modules.reactivemongo.json.BSONFormats._

/**
 * Created by mingyeung on 8/4/15.
 */
case class DbAd(
  _id: BSONObjectID,
  name: String)

case class Ad(
  name: String,
  randoms: Option[Seq[Int]],
  sessionId: Option[String],
  client_timestamp: Option[String])

object AdSerializers {
  implicit val adFormat = Json.format[Ad]
}

object DbAdSerializers {
  implicit val dbAdFormat = Json.format[DbAd]
}
