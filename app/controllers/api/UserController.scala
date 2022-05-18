package controllers.api

import models.User
import play.api.libs.json.Json
import play.api.mvc._
import dao.UserDao

import javax.inject._
import scala.concurrent.ExecutionContext

@Singleton
class UserController @Inject()(val controllerComponents: ControllerComponents, val userDao: UserDao)(implicit ec: ExecutionContext) extends BaseController{

  implicit val userFormat = Json.format[User]

  def findAll = Action.async {
    val usersFuture = userDao.findAll()
    usersFuture.map(users => Ok(Json.toJson(users)))
  }

  def findById(id: String) = Action.async {
    val userFuture = userDao.findById(id.toInt)
    userFuture.map(user => Ok(Json.toJson(user)))
  }

  def create = Action { implicit request =>
    Created("New user created")
  }

  def update(id: String) = Action {
    Ok(s"User $id updated")
  }

  def delete(id: String) = Action {
    Ok(s"User $id deleted")
  }
}
