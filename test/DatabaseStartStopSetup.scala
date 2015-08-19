package test

import reactivemongo.api.{ DefaultDB, MongoConnection, MongoDriver }
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object DatabaseStartStopSetup {

  /*
      If a new db is requqired then the database value should be ** lazy **
  */

  val TEST_DB_NAME = "ad-test-database"

  val createConnection = {
    println(" Creating Connection pool... ")
    val driver = new MongoDriver
    val connection = driver.connection(List("localhost:27017"))
    (driver, connection)
  }

  val db = {
    Await.result(createConnection._2(TEST_DB_NAME).drop(), DurationInt(2).seconds)
    createConnection._2(TEST_DB_NAME)
  }

  lazy val destoryConnectionPool = {
    println(" Closing all connections...")
    val (driver, connection) = createConnection
    Await.result(createConnection._2(TEST_DB_NAME).drop(), DurationInt(2).seconds)
    connection.askClose()(50 seconds) onComplete {
      case s => println("######### Reactive Mongo Connections stopped [ " + s + "]")
    }
    connection.actorSystem.shutdown()
    connection.close()
    driver.system.shutdown()
    driver.close()
    println("Reactive Mongo Driver Closed")

  }

}