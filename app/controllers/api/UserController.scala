package controllers.api

import dao.UserDao
import models.{User, UserForm}
import play.api.libs.json.Json
import play.api.mvc._

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(val controllerComponents: ControllerComponents, val userDao: UserDao)(implicit ec: ExecutionContext) extends BaseController {

  implicit val userFormat = Json.format[User]

  def findAll = Action.async {
    val usersFuture = userDao.findAll()
    usersFuture.map(users => Ok(Json.toJson(users)))
  }

  def findById(id: String) = Action.async {
    val userFuture = userDao.findById(id.toInt)
    userFuture.map(user => {
      println(user)
      Ok(Json.toJson(user))
    }
    )
  }

  def create = Action.async { implicit request =>
    UserForm.form.bindFromRequest.fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Something went wrong!"))
      },
      data => {
        val user = User(0, data.name, data.isAdmin)
        val userFuture = userDao.create(user)
        userFuture.map(userId => Created(s"User $userId created"))
      }
    )
  }

  def update(id: String) = Action.async { implicit request: Request[AnyContent] =>
    UserForm.form.bindFromRequest.fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Something went wrong!"))
      },
      data => {
        val user = User(id.toInt, data.name, data.isAdmin)
        val userFuture = userDao.update(user)
        userFuture.map(_ => Ok(s"User $id updated"))
      }
    )
  }

  def delete(id: String) = Action.async { implicit request: Request[AnyContent] =>
    UserForm.form.bindFromRequest.fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Something went wrong!"))
      },
      data => {
        val userFuture = userDao.delete(id.toInt)
        userFuture.map(_ => Ok(s"User $id deleted"))
      }
    )
  }
}
