package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark21_RDD_Operator_Transform {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - Key - Value类型算子
    //join

    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))
    val rdd2 = sc.makeRDD(List(("a", 4), ("b", 5), ("c", 6)))

    //join：两个不同数据源的数据，相同Key的Value会连接在一起，形成二元组
    //如果两个数据源中Key没有匹配上，那么数据不会出现在结果中
    //如果两个数据源中Key有多个相同的，会依次匹配，可能会出现笛卡尔积，数据量会几何增长，会导致性能降低
    val joinRDD: RDD[(String, (Int, Int))] = rdd1.join(rdd2)

    joinRDD.collect().foreach(println)

    sc.stop()
  }
}
