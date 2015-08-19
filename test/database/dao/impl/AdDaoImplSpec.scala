package database.dao.impl

import database.dao.AdDao
import database.dao.ReactiveMongoDao._
import models.ad.DbAd
import org.specs2.mutable.Specification
import play.api.GlobalSettings
import play.api.test.Helpers._
import play.api.test._
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.bson.BSONObjectID
import scaldi.play.ScaldiSupport
import scaldi.{ Module, Injector }
import services.AdService
import test.{ DatabaseSpec, DatabaseStartStopSetup }
import scaldi.{ Injectable, Injector }

import scala.concurrent.Await
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext

import models.ad.DbAdSerializers._

/**
 * Created by mingyeung on 8/5/15.
 */
/*

class AdDaoTestModule extends Module {
  bind[AdDao] to new AdDaoImpl("ads", DatabaseStartStopSetup.db("ads"), DatabaseStartStopSetup.db)
}

class AdDaoImplSpec extends DatabaseSpec with Injectable {

  object TestGlobal extends GlobalSettings with ScaldiSupport {
    def applicationModule = new AdServiceTestModule
  }

  implicit lazy val injector = TestGlobal.injector

  def setupDbAd(id: String): DbAd = {
    DbAd(BSONObjectID.apply(id), "ming")
  }

  "AdDao" should {

    "create an ad record" in {
      running(FakeApplication(withGlobal = Some(TestGlobal))) {
        val adDao = inject[AdDao]
        Await.result(adDao.insert(setupDbAd(BSONObjectID.generate.stringify)), DurationInt(5).seconds)
        success("insert ad successful")
      }
    }

    "create an view ad record" in {
      running(FakeApplication(withGlobal = Some(TestGlobal))) {
        val adDao = inject[AdDao]

        val id = BSONObjectID.generate.stringify
        Await.result(adDao.insert(setupDbAd(id)), DurationInt(5).seconds)
        val Some(ad) = Await.result(adDao.getAd(id), DurationInt(5).seconds)

        ad._id.stringify == id must_== true
        ad.name == "ming" must_== true

        success("insert ad and view ad successful")
      }
    }
  }
}
*/
