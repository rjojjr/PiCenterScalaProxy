package com.kirchnersolutions.picenter.scala.proxy.routes

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, get}
import com.kirchnersolutions.picenter.scala.proxy.client.Client.summary
import com.kirchnersolutions.picenter.scala.proxy.constants.PiCenterConstants

import scala.concurrent.ExecutionContext

trait SummaryRouter {
  def summaryRoute(implicit ec: ExecutionContext, ac: ActorSystem) = path(PiCenterConstants.SUMMARY_ENDPOINT){

    get{
        val res = summary()
        complete(res)
      }
  }
}
