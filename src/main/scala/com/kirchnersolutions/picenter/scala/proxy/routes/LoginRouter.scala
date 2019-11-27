package com.kirchnersolutions.picenter.scala.proxy.routes

import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post}
import com.kirchnersolutions.picenter.scala.proxy.client.Client.logon
import com.kirchnersolutions.picenter.scala.proxy.constants.PiCenterConstants

import scala.concurrent.ExecutionContext

trait LoginRouter {
  def loginRoute(implicit ec: ExecutionContext) = path(PiCenterConstants.LOGIN_ENDPOINT){
    post{
      entity(as[PiCenterConstants.LogonForm]) { logonForm =>
        val res = logon(logonForm)
        complete(res)
      }
    }
  }
}
