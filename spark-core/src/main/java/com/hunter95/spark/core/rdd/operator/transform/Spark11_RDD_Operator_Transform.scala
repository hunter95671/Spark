package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark11_RDD_Operator_Transform {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - repartition
    //扩大分区

    val rdd=sc.makeRDD(List(1,2,3,4,5,6),2)

    //coalesce算子可以扩大分区，但如果不进行shuffle操作，是没有意义的，不起作用
    //如果想扩大分区，需要shuffle操作
    //缩减分区：coalesce算子，开启shuffle可以均衡数据
    //扩大分区：repartition算子，底层调用coalesce，采用shuffle
    //val newRDD: RDD[Int] = rdd.coalesce(3,true)

    val newRDD: RDD[Int] = rdd.repartition(3)

    newRDD.saveAsTextFile("output")

    sc.stop()
  }
}
