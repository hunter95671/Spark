package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark05_RDD_Operator_Transform {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - glom
    //将每个分区转化为array数组进行处理
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)

    // Int => Array
    val glomRDD: RDD[Array[Int]] = rdd.glom()

    glomRDD.collect().foreach(data=> println(data.mkString(",")))

    sc.stop()
  }
}
