package com.hunter95.spark.core.framework.util

import org.apache.spark.SparkContext

object ENVUtil {

  private val scLocal=new ThreadLocal[SparkContext]()

  def put(sc:SparkContext):Unit={
    scLocal.set(sc)
  }

  def take():SparkContext={
    scLocal.get()
  }

  def clear():Unit={
    scLocal.remove()
  }
}
