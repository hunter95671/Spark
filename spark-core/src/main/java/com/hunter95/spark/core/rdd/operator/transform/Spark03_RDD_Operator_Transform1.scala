package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark03_RDD_Operator_Transform1 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - mapPartitionsWithIndex
    val rdd = sc.makeRDD(List(1, 2, 3, 4))

    //得到每个值所在的分区号
    val mapRDD: RDD[(Int, Int)] = rdd.mapPartitionsWithIndex(
      (index, iter) => {
        iter.map(
          num => (index, num)
        )
      }
    )

    mapRDD.collect().foreach(println)

    sc.stop()
  }
}
