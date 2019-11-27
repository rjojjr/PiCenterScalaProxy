package com.kirchnersolutions.picenter.scala.proxy.client.traits

import java.net.InetAddress

trait AddressParser {

  def getIP(hostName: String): String = {
    val inetAddress: InetAddress = InetAddress.getByName(hostName)
    inetAddress.getHostAddress
  }

  def getUri(protocol: String, hostName: String, port: String, ip: String => String): String = {
    val ipAddress = ip(hostName)
    s"$protocol${ipAddress}:$port/"
  }

}
