package contexts

import play.libs.Akka

import scala.concurrent.ExecutionContext

/**
 * Created by mingyeung on 8/3/15.
 */
object PlatformContext {
  implicit val adServerContext: ExecutionContext = Akka.system.dispatchers.lookup("akka.actor.adServer-global-dispatcher")
  implicit val randomServiceContext: ExecutionContext = Akka.system.dispatchers.lookup("akka.actor.randomService-dispatcher")
}
