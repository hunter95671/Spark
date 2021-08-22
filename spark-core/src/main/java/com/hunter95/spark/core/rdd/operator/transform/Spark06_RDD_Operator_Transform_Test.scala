package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark06_RDD_Operator_Transform_Test {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - groupBy
    val rdd = sc.textFile("datas/apache.log")

    val mapRDD: RDD[(String, Int)] = rdd.map(
      line => {
        val hour = line.split(":")(1)
        (hour, 1)
      }
    )

    val groupByRDD: RDD[(String, Iterable[(String, Int)])] = mapRDD.groupBy(_._1)

    //自己的map写法
    //map算子每次取出里面完整的一个RDD[(String, Iterable[(String, Int)])]进行计算
    val timeRDD: RDD[(String, Int)] = groupByRDD.map(hour => (hour._1, hour._2.size))

    //老师的map模式匹配写法
    //    val timeRDD: RDD[(String, Int)] = groupByRDD.map {
    //      case (hour, iter) => (hour, iter.size)
    //    }

    timeRDD.collect().foreach(println)

    sc.stop()
  }
}
