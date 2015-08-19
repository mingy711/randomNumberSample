import org.joda.time.format.ISODateTimeFormat
import play.api.libs.json._
import play.modules.reactivemongo.json.BSONFormats.PartialFormat
import reactivemongo.bson._

/**
 * Created by mingyeung on 8/4/15.
 */
package object models {
  implicit object BSONObjectIDFormat extends PartialFormat[BSONObjectID] {
    def partialReads: PartialFunction[JsValue, JsResult[BSONObjectID]] = {
      case JsObject(("$oid", JsString(v)) +: Nil) => JsSuccess(BSONObjectID(v))
    }
    val partialWrites: PartialFunction[BSONValue, JsValue] = {
      case oid: BSONObjectID => Json.obj("$oid" -> oid.stringify)
    }
  }

  implicit def bsonDocumentToJsObject(bson: BSONDocument) = JsObject(bson.elements.toList.map(t ⇒ (t._1, t._2: JsValue)))

  implicit def bsonValueToJsValue(bson: BSONValue): JsValue = bson match {
    case BSONString(value)   ⇒ JsString(value)
    case BSONDouble(value)   ⇒ JsNumber(value)
    case BSONInteger(value)  ⇒ JsNumber(value)
    case BSONLong(value)     ⇒ JsNumber(value)
    case BSONBoolean(value)  ⇒ JsBoolean(value)
    case BSONDateTime(value) ⇒ JsString(ISODateTimeFormat.dateTime().print(value))
    case BSONNull            ⇒ JsNull
    case arr: BSONArray      ⇒ JsArray(arr.values.toList.map(t ⇒ t: JsValue))
    case obj: BSONDocument   ⇒ obj: JsObject
  }
}
