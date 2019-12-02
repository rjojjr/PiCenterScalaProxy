package com.kirchnersolutions.picenter.scala.proxy.objects

import com.kirchnersolutions.picenter.scala.proxy.models.RoomSummary

trait SummaryParser {

  def parseSummaries(summaries: Seq[RoomSummary]): Unit = {
    summaries.foreach(summary => {
      printSummary(summary)
    })
  }

  private def printSummary(summary: RoomSummary): Unit ={
    println(summary.roomName)
    for(i <- 0 to 6){
      i match {
        case 0 => {
          println(s"NOW t = ${summary.temps(0)}" +
            s" h = ${summary.humiditys(0)} ts = ${summary.tempDevi(0)} hs = ${summary.humidityDevi(0)}")
        }
        case 1 => {
          println(s"1 HR t = ${summary.temps(1)}" +
            s" h = ${summary.humiditys(1)} ts = ${summary.tempDevi(1)} hs = ${summary.humidityDevi(1)}")
        }
        case 2 => {
          println(s"2 HR t = ${summary.temps(2)}" +
            s" h = ${summary.humiditys(2)} ts = ${summary.tempDevi(2)} hs = ${summary.humidityDevi(2)}")
        }
        case 3 => {
          println(s"3 HR t = ${summary.temps(3)}" +
            s" h = ${summary.humiditys(3)} ts = ${summary.tempDevi(3)} hs = ${summary.humidityDevi(3)}")
        }
        case 4 => {
          println(s"6 HR t = ${summary.temps(4)}" +
            s" h = ${summary.humiditys(4)} ts = ${summary.tempDevi(4)} hs = ${summary.humidityDevi(4)}")
        }
        case 5 => {
          println(s"12 HR t = ${summary.temps(5)}" +
            s" h = ${summary.humiditys(5)} ts = ${summary.tempDevi(5)} hs = ${summary.humidityDevi(5)}")
        }
        case 6 => {
          println(s"24 HR t = ${summary.temps(6)}" +
            s" h = ${summary.humiditys(6)} ts = ${summary.tempDevi(6)} hs = ${summary.humidityDevi(6)}")
        }
        case _ => {
          println()
        }
      }
    }
  }

}
