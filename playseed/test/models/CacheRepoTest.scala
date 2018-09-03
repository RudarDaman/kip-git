package models

import org.specs2.mutable.Specification

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created By RUDAR DAMAN SINGLA on 22/8/18
  */
class CacheRepoTest extends Specification {

  val cacheRepo = new ModelsTest[CacheRepo]
  val userRepo = UserRepo(1L, "a@b.com", Some("ABCD"), "password", 21)

  "Cache Repo " should {

    "store a user" in {
      val storeResult = cacheRepo.repository.store(userRepo)
      Await.result(storeResult, Duration.Inf) must equalTo (1)
    }

  }

}
