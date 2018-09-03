package controllers

import forms.UserForm
import javax.inject.Inject
import models.{CacheRepo, UserRepo}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Application @Inject()(controllerComponent: ControllerComponents,
                            userForm: UserForm,
                            cacheRepo: CacheRepo) extends AbstractController( controllerComponent ) {

  def index: Action[AnyContent] = Action.async { implicit request =>

    Future.successful( Ok( views.html.register(userForm.userForm) ) )
  }

  def register: Action[AnyContent] = Action.async { implicit request =>
    userForm.userForm.bindFromRequest.fold(
      formWithError => {
        Future.successful( Ok(views.html.register(formWithError)) )
      },
      data => {
        cacheRepo.get( data.email ).flatMap { optionalUser =>
          optionalUser.fold {
            val dbPayload: UserRepo = UserRepo(0, data.email, data.name, data.password.password, data.age)
            cacheRepo.store( dbPayload ).map {
              case _ : Long => Redirect( routes.Application.getUser( data.email ) )
              case _ => InternalServerError( "Something went wrong!" )
            }
          } { _ =>
            Future.successful( Ok( "User already exists." ) )
          }
        }
      }
    )
  }

  def getUser(email: String): Action[AnyContent] = Action.async { implicit request =>
    val session = request.session.get("login").getOrElse("")
    request.session.
    cacheRepo.get( email ).map { optionalUser =>
      optionalUser.fold {
        NotFound( "User doesn't exist!" )
      } { user =>
        Ok( s"${user.email}, ${user.name}, ${user.age}" )
      }
    }
  }

  def index2: Action[AnyContent] = Action.async { implicit request =>
    Redirect(routes.Application.index().withSession("login" -> "yes"))
  }

}