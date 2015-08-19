package test

import org.specs2.mutable._
import org.specs2.specification._

/**
 * Created by mingyeung on 8/5/15.
 */
trait DatabaseSpec extends Specification {

  override def map(fs: => Fragments) = Step(DatabaseStartStopSetup.createConnection) ^ fs ^ Step(DatabaseStartStopSetup.destoryConnectionPool)
}
