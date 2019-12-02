package com.kirchnersolutions.picenter.scala.proxy.objects

import com.kirchnersolutions.picenter.scala.proxy.models.RestUser
import com.kirchnersolutions.picenter.scala.proxy.traits.RestObject

class User {

  var username: String
  var 

  def this(user: RestUser) = {
    this();
    this.user =
    def apply (): User = new User ()
  }

  def getToken():String = {
    this.user.token
  }

}
object Use
