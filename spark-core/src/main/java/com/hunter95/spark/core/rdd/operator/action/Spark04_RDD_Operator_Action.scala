package com.hunter95.spark.core.rdd.operator.action

import org.apache.spark.{SparkConf, SparkContext}

object Spark04_RDD_Operator_Action {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(1, 1, 3, 4))
    val rdd2 = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3)))

    //行动算子
    //countByValue & countByKey

    //countByValue：统计每种Value的个数
    val intToLong: collection.Map[Int, Long] = rdd1.countByValue()
    println(intToLong)

    //countByKey：统计每种key的个数
    val stringToLong: collection.Map[String, Long] = rdd2.countByKey()
    println(stringToLong)

    sc.stop()
  }
}
