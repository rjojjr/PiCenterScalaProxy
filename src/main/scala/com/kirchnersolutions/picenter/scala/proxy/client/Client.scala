package com.kirchnersolutions.picenter.scala.proxy.client

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.FromEntityUnmarshaller
import akka.stream.ActorMaterializer
import java.net._

import com.kirchnersolutions.picenter.scala.proxy.client.traits.AddressParser
import io.circe.syntax._
import com.kirchnersolutions.picenter.scala.proxy.constants.PiCenterConstants._
import com.kirchnersolutions.picenter.scala.proxy.traits.ConfigValues
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

object Client extends AddressParser with ConfigValues {



  def logon(form: LogonForm)(implicit ec: ExecutionContext, responseUnmarshaller: FromEntityUnmarshaller[RestResponse]) = {

    val json = form.asJson
    val postEntity = json.toString()
    //val url = getUri(PROTOCOL, HOST_NAME, PORT, getIP)
    val url = getUri(protocol, host_name, host_port, getIP)
    val req = HttpRequest(
      method = HttpMethods.POST,
      uri = url + LOGIN_ENDPOINT,
      entity = HttpEntity(ContentTypes.`application/json`, postEntity)
    )

    val responseFuture: Future[HttpResponse] = Http().singleRequest(req)
    responseFuture.flatMap {
      case response @ HttpResponse(StatusCodes.OK, _, _, _) if (response.entity.contentType == ContentTypes.`application/json`) =>
          val entity = response.entity
          responseUnmarshaller(entity)
      case _ => Future.failed(new RuntimeException("something went wrong"))
    }

  }

  def logOut()(implicit ec: ExecutionContext, responseUnmarshaller: FromEntityUnmarshaller[RestResponse]) = {

    val url = getUri(protocol, host_name, host_port, getIP)
    val uri = url + LOGOUT_ENDPOINT

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = uri))
    responseFuture.flatMap {
      case response @ HttpResponse(StatusCodes.OK, _, _, _) if (response.entity.contentType == ContentTypes.`application/json`) =>
        val entity = response.entity
        responseUnmarshaller(entity)
      case _ =>
        Future.failed(new RuntimeException("something went wrong"))
    }
  }

  def summary()(implicit ec: ExecutionContext, responseUnmarshaller: FromEntityUnmarshaller[RestResponse]) = {

    val url = getUri(protocol, host_name, host_port, getIP)
    val uri = url + SUMMARY_ENDPOINT
  }

}
