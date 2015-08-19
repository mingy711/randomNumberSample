package modules

import controllers._
import scaldi.Module

/**
 * Created by mingyeung on 8/3/15.
 */
class WebModule extends Module {
  binding to new AdController
}
