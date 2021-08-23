package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark19_RDD_Operator_Transform {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - Key - Value类型算子
    //combineByKey

    val rdd = sc.makeRDD(List(("a", 1), ("a", 2), ("b", 3), ("b", 4), ("b", 5), ("a", 6)), 2)

    //combineByKey：需要三个参数
    //第一个参数表示：将相同Key的第一个数据进行结构转换，实现操作，如：v=>(v,1)
    //第二个参数表示：分区内的计算规则
    //第三个参数表示：分区间的计算规则
    val aggRDD: RDD[(String, (Int, Int))] = rdd.combineByKey(
      v => (v, 1),
      (t: (Int, Int), v) => (t._1 + v, t._2 + 1),
      (t1: (Int, Int), t2: (Int, Int)) => (t1._1 + t2._1, t1._2 + t2._2)
    )

    val resultRDD: RDD[(String, Int)] = aggRDD.mapValues(t => t._1 / t._2)

    resultRDD.collect().foreach(println)

    sc.stop()
  }
}
