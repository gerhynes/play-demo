package models

import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import slick.jdbc.PostgresProfile.api._

case class User(id: Int, name : String, isAdmin: Boolean)

case class UserFormData(name: String, isAdmin: Boolean)

object UserForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "isAdmin" -> boolean
    )(UserFormData.apply)(UserFormData.unapply)
  )
}

class UsersTable(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def isAdmin = column[Boolean]("is_admin")

  def * = (id, name, isAdmin) <> (User.tupled, User.unapply)
}
