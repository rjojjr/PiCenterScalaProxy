package com.kirchnersolutions.picenter.scala.proxy.models

import com.kirchnersolutions.picenter.scala.proxy.traits.RestObject

case class RestUser(userName: String,
                    token: String,
                    page: String,
                    ip: String,
                    stompId: String)

object RestUser extends RestObject
