package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark05_RDD_Operator_Transform_Par {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - map
    //转换后，分区不变

    val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)
    rdd.saveAsTextFile("output")

    //【1,2】【3,4】
    //【2,3】【6,8】

    val mapRDD: RDD[Int] = rdd.map(_ * 2)

    mapRDD.saveAsTextFile("output1")
    sc.stop()
  }
}
