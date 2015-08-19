package modules

import database.dao.ReactiveMongoDao._
import database.dao.AdDao
import database.dao.impl.AdDaoImpl
import play.modules.reactivemongo.json.collection.JSONCollection
import scaldi.Module
import services.AdService

/**
 * Created by mingyeung on 8/4/15.
 */
class AdModule extends Module {
  bind[AdDao] to new AdDaoImpl("ads", db.collection[JSONCollection]("ads"), db)

  bind[AdService] to new AdService()
}
