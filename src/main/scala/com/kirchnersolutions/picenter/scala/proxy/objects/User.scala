package com.kirchnersolutions.picenter.scala.proxy.objects

import com.kirchnersolutions.picenter.scala.proxy.models.RestUser
class User {
  var username: String = ""
  var token: String = ""

  def this(user: RestUser) = {
    this()
    this.username = user.userName
    this.token = user.token
  }

  def getToken():String = {
    this.token
  }

  def getUsername(): String = {
    this.username
  }
}
