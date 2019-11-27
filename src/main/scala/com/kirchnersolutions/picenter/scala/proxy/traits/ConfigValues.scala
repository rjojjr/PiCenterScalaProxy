package com.kirchnersolutions.picenter.scala.proxy.traits

import com.kirchnersolutions.picenter.scala.proxy.WebService.config
import com.typesafe.config.ConfigFactory

trait ConfigValues {

  implicit val config = ConfigFactory.load()
  implicit val host_name = config.getString("host.host_name")
  implicit val protocol = config.getString("host.protocol")
  implicit val host_port = config.getString("host.host_port")
  val host = config.getString("http.host") // Gets the host and a port from the configuration
  val port = config.getInt("http.port")

}
