package com.kirchnersolutions.picenter.scala.proxy.client.traits

import java.net.InetAddress

trait Addresses {

  def getIP(hostName: String): String = {
    val inetAddress: InetAddress = InetAddress.getByName(hostName)
    inetAddress.getHostAddress
  }

  def getUri(protocol: String, hostName: String, port: Int, ip: String => String): String = {
    val ipAddress = ip(hostName)
    s"$protocol://${ipAddress}:$port/"
  }

}
