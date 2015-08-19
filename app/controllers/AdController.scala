package controllers

import models.random.RandomServiceException
import play.api.http.Status._
import play.api.libs.json.{ Json, JsNull }
import play.api.mvc.Results._
import play.api.mvc.{ AnyContent, Action, Controller }
import scaldi.{ Injectable, Injector }
import contexts.PlatformContext.adServerContext
import services.AdService
import models.ad.AdSerializers._

import scala.concurrent.Future

/**
 * Created by mingyeung on 8/3/15.
 */
class AdController(implicit inj: Injector) extends Controller with Injectable {

  val adService = inject[AdService]

  def getAd(appId: String, client_timestamp: String) = Action.async { request =>
    adService.getAd(appId, client_timestamp).flatMap {
      ad =>
        Future.successful(Ok(Json.obj(
          "ad" -> ad
        )))
    }
  }
}
