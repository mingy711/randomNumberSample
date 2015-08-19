package database.dao

import contexts.PlatformContext.adServerContext
import play.api.Play

/**
 * Created by mingyeung on 8/4/15.
 */
object ReactiveMongoDao {
  val config = play.api.Play.current.plugin[database.mongoconfig.MongoDBPlugin] getOrElse (throw new Exception("MongoDBPlugin cannot be loaded."))
  val driver = config.driver
  val connection = config.connection
  val db = connection.db(Play.current.configuration.getString("mongodb.db").getOrElse("celtra"))
}
