package com.kirchnersolutions.picenter.scala.proxy.routers

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post}
import com.kirchnersolutions.picenter.scala.proxy.client.Client
import com.kirchnersolutions.picenter.scala.proxy.client.Client.logon
import com.kirchnersolutions.picenter.scala.proxy.constants.PiCenterConstants
import com.kirchnersolutions.picenter.scala.proxy.models.LogonForm

import scala.concurrent.ExecutionContext

trait LoginRouter {
  def loginRoute(implicit ec: ExecutionContext, ac: ActorSystem) = path(PiCenterConstants.LOGIN_ENDPOINT){

    post{
      entity(as[LogonForm]) { logonForm =>
        val res = logon(logonForm)
        res.foreach(x => {
          Client.token = x.restUser.token
          println(Client.token)
        })
        complete(res)
      }
    }
  }
}
