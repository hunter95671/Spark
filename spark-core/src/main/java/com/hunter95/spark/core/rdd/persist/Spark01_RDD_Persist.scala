package com.hunter95.spark.core.rdd.persist

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Persist {

  def main(args: Array[String]): Unit = {
    val sparConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparConf)

    val list = List("Hello scala", "Hello spark")

    val rdd = sc.makeRDD(list)

    val flatRDD = rdd.flatMap(_.split(" "))

    val mapRDD: RDD[(String, Int)] = flatRDD.map((_, 1))

    val reduceRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_ + _)

    reduceRDD.collect().foreach(println)
    println("=========================")

    val list1 = List("Hello scala", "Hello spark")

    val rdd1 = sc.makeRDD(list)

    val flatRDD1 = rdd.flatMap(_.split(" "))

    val mapRDD1: RDD[(String, Int)] = flatRDD.map((_, 1))

    val groupRDD: RDD[(String, Iterable[Int])] = mapRDD1.groupByKey()

    groupRDD.collect().foreach(println)
  }
}
