package dao

import models.{User, UsersTable}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class UserDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  val users = TableQuery[UsersTable]

  def findAll(): Future[Seq[User]] = db.run(users.result)

  def findById(id: Int): Future[User] = db.run(users.filter(_.id === id).result.head)

  def create(user: User): Future[Int] = db.run(users.returning(users.map(_.id))+= user)

  def update(user: User): Future[Int] = db.run(users.filter(_.id === user.id).update(user))

  def delete(id: Int): Future[Int] = db.run(users.filter(_.id === id).delete)
}
