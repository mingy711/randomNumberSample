package database.mongoconfig

import play.api._
import play.Plugin
import play.api.Application
import reactivemongo.api.{ MongoConnection, MongoDriver }
import collection.JavaConverters._

/**
 * Created by mingyeung on 8/4/15.
 */
class MongoDBPlugin(val app: Application) extends Plugin {

  lazy val (driver: MongoDriver, connection: MongoConnection) = {
    val mDriver = new MongoDriver
    val mConnection = mDriver.connection(Play.current.configuration.getStringList("mongodb.servers").map(_.asScala.toList).get)
    (mDriver, mConnection)
  }

  override def onStart() {
    (driver, connection)
  }

  override def onStop() {
    // shutdown actor system
    connection.close()
  }

  override lazy val enabled = {
    //!app.configuration.getString("mongodbplugin").filter(_ == "disabled").isDefined
    true

  }
}
