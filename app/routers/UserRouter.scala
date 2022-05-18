package routers

import controllers.api.UserController
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

import javax.inject.Inject

class UserRouter @Inject()(controller: UserController) extends SimpleRouter{
  val prefix = "api/v1/users"

  override def routes: Routes = {
    case GET(p"/") => controller.findAll
    case GET(p"/$id") => controller.findById(id)
    case POST(p"/") => controller.create()
    case PUT(p"/$id") => controller.update(id)
    case DELETE(p"/$id") => controller.delete(id)
  }
}
