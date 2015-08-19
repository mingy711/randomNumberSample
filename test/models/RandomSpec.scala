package models

import database.dao.AdDao
import database.dao.impl.AdDaoImpl
import models.random.RandomManager
import org.specs2.mutable.Specification
import play.api.test.FakeApplication
import play.api.test.Helpers._
import scaldi.Module
import test.DatabaseStartStopSetup

import scala.concurrent.duration._

import scala.concurrent.{ duration, Await }

/**
 * Created by mingyeung on 8/6/15.
 */
class RandomSpec extends Specification {
  "Random Test" should {

    "get random number" in {
      running(FakeApplication()) {
        val result = Await.result(RandomManager.getRandom(), DurationInt(5).seconds)

        result.fold(
          error => false,
          randomNumber => true
        )
      }
    }
  }
}
