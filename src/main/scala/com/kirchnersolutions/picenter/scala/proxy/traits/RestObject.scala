package com.kirchnersolutions.picenter.scala.proxy.traits

import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport
import io.circe.generic.AutoDerivation

trait RestObject extends AutoDerivation with ErrorAccumulatingCirceSupport
