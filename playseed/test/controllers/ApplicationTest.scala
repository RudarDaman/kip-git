package controllers

import forms.UserForm
import models.CacheRepo
import org.scalatestplus.play.PlaySpec
import org.specs2.mock.Mockito
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.test.CSRFTokenHelper._
import org.mockito.Mockito.when

import scala.concurrent.Future

/**
  * Created By RUDAR DAMAN SINGLA on 22/8/18
  */
class ApplicationTest extends PlaySpec with Mockito {

  "Application Controller" should {

    "render registration page" in {

      val mockedCacheRepo = mock[CacheRepo]
      val mockedUserForm = mock[UserForm]

      val controller = new Application(stubControllerComponents(), mockedUserForm, mockedCacheRepo)

      val userForm = new UserForm{}.userForm

      when(mockedUserForm.userForm) thenReturn userForm

      val result = controller.index().apply(FakeRequest(GET, "/register").withCSRFToken)
      contentType(result) mustBe Some("text/html")
      contentAsString(result) must include("HEADER")

    }

    "get users by email" in {

      val mockedCacheRepo = mock[CacheRepo]
      val mockedUserForm = mock[UserForm]

      val controller = new Application(stubControllerComponents(), mockedUserForm, mockedCacheRepo)

      when(mockedCacheRepo.get("test@example.com")) thenReturn Future.successful(None)

      val result = controller.getUser("test@example.com").apply(FakeRequest(GET, "/register").withCSRFToken)

      status(result) must equal (NOT_FOUND)

    }

  }

}
