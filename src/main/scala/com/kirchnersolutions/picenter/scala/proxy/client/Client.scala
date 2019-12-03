package com.kirchnersolutions.picenter.scala.proxy.client

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.FromEntityUnmarshaller
import com.kirchnersolutions.picenter.scala.proxy.client.traits.Addresses
import com.kirchnersolutions.picenter.scala.proxy.constants.PiCenterConstants._
import com.kirchnersolutions.picenter.scala.proxy.models._
import akka.http.scaladsl.model.headers.{Cookie, HttpCookie, `Set-Cookie`}
import com.kirchnersolutions.picenter.scala.proxy.traits.ConfigValues
import io.circe.syntax._

import scala.concurrent.{ExecutionContext, Future}
import com.kirchnersolutions.picenter.scala.proxy.objects.User

object Client extends Addresses with ConfigValues {

  var token: String = ""
  var cookie: Seq[HttpCookie] = Nil

  def logon(form: LogonForm)(implicit ec: ExecutionContext, ac: ActorSystem, responseUnmarshaller: FromEntityUnmarshaller[RestResponse]) = {

    val json = form.asJson
    val postEntity = json.toString()
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
        val setCookies = response.headers[`Set-Cookie`]
          responseUnmarshaller(entity)
      case _ => Future.failed(new RuntimeException("something went wrong"))
    }

  }

  def logOut()(implicit ec: ExecutionContext, ac: ActorSystem, responseUnmarshaller: FromEntityUnmarshaller[RestResponse]) = {

    val url = getUri(protocol, host_name, host_port, getIP)
    val uri = url + LOGOUT_ENDPOINT

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = uri, headers = cookie))
    responseFuture.flatMap {
      case response @ HttpResponse(StatusCodes.OK, _, _, _) if (response.entity.contentType == ContentTypes.`application/json`) =>
        val entity = response.entity
        val setCookies = response.headers[`Set-Cookie`]
        responseUnmarshaller(entity)
      case _ =>
        Future.failed(new RuntimeException("something went wrong"))
    }
  }

  def summary()(implicit ec: ExecutionContext, ac: ActorSystem, responseUnmarshaller: FromEntityUnmarshaller[RestResponse]) = {

    val url = getUri(protocol, host_name, host_port, getIP)
    //val uri = url + SUMMARY_ENDPOINT
    val uri = s"${url}${SUMMARY_ENDPOINT}?userId=${token}"

    val cookieHeader = akka.http.scaladsl.model.headers.Cookie(cookie)
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = uri, headers = cookie))
    responseFuture.flatMap {
      case response @ HttpResponse(StatusCodes.OK, _, _, _) if (response.entity.contentType == ContentTypes.`application/json`) =>
        val entity = response.entity
        val cookies = response.headers.collect {
          case c: `Set-Cookie` => c.cookie
        }
        val setCookies = response.headers[`Set-Cookie`]
        cookie = cookies
        responseUnmarshaller(entity)
      case _ =>
        Future.failed(new RuntimeException("something went wrong"))
    }
  }

}
