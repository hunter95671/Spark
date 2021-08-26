package com.hunter95.spark.core.framework.common

import com.hunter95.spark.core.framework.util.ENVUtil

trait TDao {
  def readFile(path: String) = {

    ENVUtil.take().textFile(path)

  }
}
