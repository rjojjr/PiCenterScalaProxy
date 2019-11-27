package com.kirchnersolutions.picenter.scala.proxy.models

import com.kirchnersolutions.picenter.scala.proxy.traits.RestObject

case class LogonForm(username: String,
                     password: String)

object LogonForm extends RestObject
