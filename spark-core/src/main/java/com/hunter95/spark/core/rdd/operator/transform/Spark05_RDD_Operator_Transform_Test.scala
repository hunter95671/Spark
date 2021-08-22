package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark05_RDD_Operator_Transform_Test {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - glom
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)

    //【1,2】 【3,4】
    //【2】 【4】
    //【6】

    //先将每个分区转化为array数组
    val glomRDD: RDD[Array[Int]] = rdd.glom()

    //取出每个分区最大值
    val maxRDD: RDD[Int] = glomRDD.map(
      array => array.max
    )

    println(maxRDD.collect().sum)

    sc.stop()
  }
}
