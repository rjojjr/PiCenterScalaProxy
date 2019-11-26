package com.kirchnersolutions.picenter.scala.proxy.client

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.FromEntityUnmarshaller
import akka.stream.ActorMaterializer

import io.circe.syntax._

import com.kirchnersolutions.picenter.scala.proxy.constants.PiCenterConstants.{LogonForm, RestResponse, HOST_NAME, LOGIN_ENDPOINT}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

object Client extends {

  def logon(form: LogonForm)(implicit ec: ExecutionContext, responseUnmarshaller: FromEntityUnmarshaller[RestResponse]) = {

    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
    val json = form.asJson
    val postEntity = json.toString()
    val req = HttpRequest(
      method = HttpMethods.POST,
      uri = HOST_NAME + "/" + LOGIN_ENDPOINT,
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

}
