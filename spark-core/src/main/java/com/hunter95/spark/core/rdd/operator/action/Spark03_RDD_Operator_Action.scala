package com.hunter95.spark.core.rdd.operator.action

import org.apache.spark.{SparkConf, SparkContext}

object Spark03_RDD_Operator_Action {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    val rdd=sc.makeRDD(List(1,2,3,4))

    //行动算子

    rdd.aggregate(0)(_+_)(_+_)

    sc.stop()
  }
}
