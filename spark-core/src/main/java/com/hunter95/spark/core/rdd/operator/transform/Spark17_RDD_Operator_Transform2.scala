package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark17_RDD_Operator_Transform2 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - Key - Value类型算子
    //foldByKey

    val rdd = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3), ("a", 1)), 2)
    //如果聚合计算时，分区内核分区间的计算规则相同时，可以使用foldByKey
    //第一个参数列表，需要传递一个参数，表示初始值
    //主要用于当碰见第一个Key的时候，和Value进行分区内计算
    //第二个参数列表需要传递两个参数，第一个表示分区内计算规则，第二个表示分区间计算规则

    val foldRDD: RDD[(String, Int)] = rdd.foldByKey(0)(_ + _)

    foldRDD.collect().foreach(println)

    sc.stop()
  }
}
