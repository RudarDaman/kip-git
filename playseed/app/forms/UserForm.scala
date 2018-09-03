package forms

import play.api.data.Form
import play.api.data.Forms.{mapping, email, optional, text, number}

case class Password(password: String, confirmPassword: String)
case class User(email: String, name: Option[String], password: Password, age: Int)

class UserForm {
  val userForm = Form(
    mapping(
      "email" -> email,
      "name" -> optional( text ),
      "passwordGroup" -> mapping(
        "password" -> text.verifying("password must not be empty", _.nonEmpty),
        "confirmpassword" -> text.verifying("Confirm password must not be empty", _.nonEmpty)
      )(Password.apply )( Password.unapply )
        .verifying("Password and Confirm Password must match.", passwordGroup =>
          passwordGroup.password == passwordGroup.confirmPassword
        ),
      "age" -> number
    )( User.apply )( User.unapply )
  )
}