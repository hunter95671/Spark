package com.hunter95.spark.core.acc

import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Acc {
  def main(args: Array[String]): Unit = {
    val sparConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparConf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4))

    //获取系统累加器
    //Spark默认就提供了简单数据聚合的累加器
    //sc.longAccumulator
    //sc.doubleAccumulator
    //sc.collectionAccumulator
    val sumAcc: LongAccumulator = sc.longAccumulator("sum")

    //使用累加器
    rdd.foreach(sumAcc.add(_))

    //获取累加器的值
    println(sumAcc.value)

    sc.stop()
  }
}
