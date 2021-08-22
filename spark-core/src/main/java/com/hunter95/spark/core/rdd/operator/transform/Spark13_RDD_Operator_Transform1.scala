package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark13_RDD_Operator_Transform1 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - 双Value类型算子

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4), 2)
    //val rdd2=sc.makeRDD(List(3,4,5,6),4)
    val rdd2 = sc.makeRDD(List(3, 4, 5, 6), 2)

    //拉链操作要求两个数据源分区数量保持一致
    //RDD拉链两个数据源每一个分区的元素数量要相同
    val rdd6: RDD[(Int, Int)] = rdd1.zip(rdd2)
    println(rdd6.collect().mkString(","))

    sc.stop()
  }
}
