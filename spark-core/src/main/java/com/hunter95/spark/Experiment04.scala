package com.hunter95.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Experiment04 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    //val rdd: RDD[String] = sc.textFile("datas/A.txt,datas/B.txt")
    val rdd: RDD[String] = sc.textFile("datas/Algorithm.txt,datas/Python.txt,datas/Database.txt")
    val rdd2=rdd.map(x=>(x.split(" ")(0),x.split(" ")(1)))
    val rdd3: RDD[(String, (Int, Int))] = rdd2.aggregateByKey((0, 0))(
      (t, v) => (t._1 + v.toInt, t._2 + 1),
      (t1, t2) => (t1._1 + t2._1, t1._2 + t2._2)
    )

    val resultRDD: RDD[(String, Int)] = rdd3.mapValues(t => t._1 / t._2)
    resultRDD.collect().foreach(println)
    sc.stop()
  }
}
