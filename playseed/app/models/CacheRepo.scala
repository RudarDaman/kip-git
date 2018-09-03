package models

import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.Future

//Using Cache Repo
/*
class CacheRepo @Inject()(cache: AsyncCacheApi){

  def store(user: User): Future[Done] = {
    cache.set(user.email, user)
  }

  def get(email: String): Future[Option[User]] = {
    cache.get(email)
  }
}*/

case class UserRepo(id: Long, email: String, name: Option[String], password: String, age: Int)

//Using Slick
class CacheRepo @Inject()(override protected val dbConfigProvider: DatabaseConfigProvider) extends Impl with UserTable {



}

trait Impl {

  self: UserTable =>

  import profile.api._

  def store(userRepo: UserRepo): Future[Long] = {

    db.run{
      userProfileQuery.schema.create
      userProfileQuery returning userProfileQuery.map(_.id) += userRepo
    }

  }

  def get(email: String): Future[Option[UserRepo]] = {
    db.run{
      userProfileQuery.filter(_.email.toLowerCase === email.toLowerCase).result.headOption
    }
  }

}

trait UserTable extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  val userProfileQuery: TableQuery[UserProfile]  = TableQuery[UserProfile]

  private[models] class UserProfile(tag: Tag) extends Table[UserRepo]( tag, "userprofile" ) {

    def id: Rep[Long] = column[Long]( "id", O.PrimaryKey, O.AutoInc )

    def email: Rep[String] = column[String]( "email" )

    def name: Rep[Option[String]] = column[Option[String]]( "name" )

    def password: Rep[String] = column[String]( "password" )

    def age: Rep[Int] = column[Int]( "age" )

    //noinspection ScalaStyle
    def * : ProvenShape[UserRepo] = (id, email, name, password, age) <> (UserRepo.tupled, UserRepo.unapply)
  }

}