package com.kirchnersolutions.picenter.scala.proxy

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.kirchnersolutions.picenter.scala.proxy.constants.PiCenterConstants
import com.kirchnersolutions.picenter.scala.proxy.client.Client.{logOut, logon}
import com.kirchnersolutions.picenter.scala.proxy.routes.{LoginRouter, LogoutRouter, SummaryRouter}
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.ExecutionContext
import scala.io.StdIn
import com.kirchnersolutions.picenter.scala.proxy.traits.ConfigValues


object WebService extends ConfigValues{

  def main(args: Array[String]): Unit ={

    implicit val system: ActorSystem = ActorSystem("actor-system")  // ActorMaterializer requires an implicit ActorSystem
    implicit val executionContextExecutor = system.dispatcher  // bindingFuture.map requires an implicit ExecutionContext
    implicit val materializer = ActorMaterializer()  // bindAndHandle requires an implicit materializer


    object MainRouter extends LoginRouter with LogoutRouter with SummaryRouter {
      val routes = loginRoute ~ logoutRoute ~ summaryRoute
    }

    val errorHandler = ExceptionHandler { case exception => complete(StatusCodes.BadRequest, exception.toString) }
    def routes = handleExceptions(errorHandler) { MainRouter.routes }
    val bindingFuture = Http().bindAndHandle(routes, host, port)

    //Comment last lines out to run ~reStart
    println(s"Server online at " + host + ":" + port + "\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      //.flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done

  }
}
