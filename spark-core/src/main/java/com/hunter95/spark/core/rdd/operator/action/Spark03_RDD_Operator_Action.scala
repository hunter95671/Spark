package com.hunter95.spark.core.rdd.operator.action

import org.apache.spark.{SparkConf, SparkContext}

object Spark03_RDD_Operator_Action {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)

    //行动算子
    //aggregate & fold
    //aggregateByKey：初始值只会参与分区内计算
    //aggregate：初始值会参与分区内计算，并且会参与分区间计算

    val result1: Int = rdd.aggregate(0)(_ + _, _ + _)

    //fold：分区内计算与分区间计算相同
    val result2: Int = rdd.fold(10)(_ + _)

    println(result1)

    sc.stop()
  }
}
