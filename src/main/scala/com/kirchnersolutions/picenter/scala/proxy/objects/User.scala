package com.kirchnersolutions.picenter.scala.proxy.objects

import com.kirchnersolutions.picenter.scala.proxy.models.RestUser
/*class User(val username: String, val token: String)

  var username: String = ""
  var token: String = ""

  def this() = {
    this()
  }

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
  }*/


object User {
  var username: String = ""
  var token: String = ""

  def set(userName: String, Token: String) = {
    username = userName
    token = Token
  }
}
