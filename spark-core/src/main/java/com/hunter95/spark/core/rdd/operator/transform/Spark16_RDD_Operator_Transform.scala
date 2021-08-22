package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark16_RDD_Operator_Transform {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - Key - Value类型算子
    //groupByKey

    val rdd = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3), ("b", 1)))

    //groupByKey：将数据源中的数据，相同的Key数据分在一个组里，形成一个对偶元组
    //           元组中的第一个元素就是Key
    //           元组中第二个元素就是相同的Key的Value集合
    val groupRDD: RDD[(String, Iterable[Int])] = rdd.groupByKey()

    groupRDD.collect().foreach(println)

    sc.stop()
  }
}
