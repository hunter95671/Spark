package com.hunter95.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Experiment0402 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[String] = sc.textFile("datas/A.txt,datas/B.txt").distinct()

    rdd.collect().foreach(println)

    sc.stop()
  }
}
