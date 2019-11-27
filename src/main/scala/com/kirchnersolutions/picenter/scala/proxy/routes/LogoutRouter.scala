package com.kirchnersolutions.picenter.scala.proxy.routes

import akka.http.scaladsl.server.Directives.{complete, get, path}
import com.kirchnersolutions.picenter.scala.proxy.client.Client.logOut
import com.kirchnersolutions.picenter.scala.proxy.constants.PiCenterConstants

import scala.concurrent.ExecutionContext

trait LogoutRouter {
  def logoutRoute(implicit ec: ExecutionContext) = path(PiCenterConstants.LOGOUT_ENDPOINT){
    get{
      val res = logOut()
      complete(res)
    }
  }
}
