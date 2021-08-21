package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark04_RDD_Operator_Transform {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - flatMap
    val rdd = sc.makeRDD(List(List(1, 2), List(3, 4)), 2)

    //flatmap 是先map再flatten
    //flat需要传入list对象,后一个list是map阶段映射后需要再传给flat的内容
    val flatRDD: RDD[Int] = rdd.flatMap(
      list => {
        list
      }
    )

    flatRDD.collect().foreach(println)

    sc.stop()
  }
}
