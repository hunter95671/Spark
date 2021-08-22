package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark07_RDD_Operator_Transform {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - filter

    val rdd = sc.makeRDD(List(1, 2, 3, 4))

    //过滤出符合filter参数内的数据
    //filter(_ % 2 == 1)过滤出奇数
    val filterRDD: RDD[Int] = rdd.filter(_ % 2 == 1)

    filterRDD.collect().foreach(println)

    sc.stop()
  }
}
