package com.kirchnersolutions.picenter.scala.proxy.routers

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, path}
import com.kirchnersolutions.picenter.scala.proxy.client.Client.summary
import com.kirchnersolutions.picenter.scala.proxy.constants.PiCenterConstants
import com.kirchnersolutions.picenter.scala.proxy.objects.SummaryParser.parseSummaries

import scala.concurrent.ExecutionContext

trait SummaryRouter {
  def summaryRoute(implicit ec: ExecutionContext, ac: ActorSystem) = path(PiCenterConstants.SUMMARY_ENDPOINT){

    get{
        val res = summary()
        res.foreach(x => {
           parseSummaries(x.summary)
        })
        complete(res)
      }
  }
}
