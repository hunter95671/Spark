package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark23_RDD_Operator_Transform {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - Key - Value类型算子
    // cogroup ：connect + group
    //分组+连接

    val rdd1 = sc.makeRDD(List(("a", 1), ("a", 2), ("c", 3)))
    val rdd2 = sc.makeRDD(List(("a", 4), ("a", 4),("b", 5)))

    val cogroupRDD: RDD[(String, (Iterable[Int], Iterable[Int]))] = rdd1.cogroup(rdd2)

    cogroupRDD.collect().foreach(println)

    sc.stop()
  }
}
