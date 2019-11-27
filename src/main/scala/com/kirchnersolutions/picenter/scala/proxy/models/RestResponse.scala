package com.kirchnersolutions.picenter.scala.proxy.models

import com.kirchnersolutions.picenter.scala.proxy.traits.RestObject

case class RestResponse(responseBody: String,
                        restUser: RestUser,
                        summary: Seq[RoomSummary])

object RestResponse extends RestObject
