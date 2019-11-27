package com.kirchnersolutions.picenter.scala.proxy.models

import com.kirchnersolutions.picenter.scala.proxy.traits.RestObject

case class RoomSummary(roomName: String,
                       temps: Seq[String],
                       humiditys: Seq[String],
                       tempDevi: Seq[String],
                       humidityDevi: Seq[String])

object RoomSummary extends RestObject
