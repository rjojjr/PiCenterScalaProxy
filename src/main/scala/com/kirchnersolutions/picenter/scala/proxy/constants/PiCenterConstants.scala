package com.kirchnersolutions.picenter.scala.proxy.constants

import com.kirchnersolutions.picenter.scala.proxy.traits.RestObject

object PiCenterConstants {

  final val HOST_NAME = "http://97.92.200.75:7733"
  final val LOGIN_ENDPOINT = "login"
  final val LOGOUT_ENDPOINT = "logout"
  final val SUMMARY_ENDPOINT = "summary"
  final val LOADING_ENDPOINT = "loading"
  final val UPDATE_ENDPOINT = "update"

  case class LogonForm(username: String,
                       password: String)

  object LogonForm extends RestObject

  case class RestUser(userName: String,
                      token: String,
                      page: String,
                      ip: String,
                      stompId: String)

  object RestUser extends RestObject

  case class RoomSummary(roomName: String,
                         temps: Seq[String],
                         humiditys: Seq[String],
                         tempDevi: Seq[String],
                         humidityDevi: Seq[String])

  object RoomSummary extends RestObject

  case class RestResponse(responseBody: String,
                          restUser: RestUser,
                          summary: Seq[RoomSummary])

  object RestResponse extends RestObject

}
