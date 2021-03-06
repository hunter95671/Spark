package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark17_RDD_Operator_Transform2 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - Key - Value类型算子
    //aggregateByKey

    val rdd = sc.makeRDD(List(("a", 1), ("a", 2), ("b", 3), ("b", 4), ("b", 5), ("a", 6)), 2)

    //aggregateByKey最终的返回类型应该和初始值的类型保持一致
    //val aggregateRDD: RDD[(String, String)] = rdd.aggregateByKey("")(_ + _, _ + _)

    //获取相同Key的数据的平均值 => (a,3) (b,4)
    //初始值传入一个(0,0)的元组，第一个元素表示value值的总和，第二个表示出现的次数
    val aggRDD: RDD[(String, (Int, Int))] = rdd.aggregateByKey((0, 0))(
      (t, v) => (t._1 + v, t._2 + 1),
      (t1, t2) => (t1._1 + t2._1, t1._2 + t2._2)
    )

    val resultRDD: RDD[(String, Int)] = aggRDD.mapValues(t => t._1 / t._2)

    resultRDD.collect().foreach(println)

    sc.stop()
  }
}
