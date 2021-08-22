package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark10_RDD_Operator_Transform {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - coalesce
    //缩减分区

    val rdd=sc.makeRDD(List(1,2,3,4,5,6),3)

    //第一个参数是预期分区数，第二是参数是是否开启shuffle

    //coalesce算子默认情况下不会将分区的数据打乱重新组合
    //这种情况下的缩减分区可能会导致数据不均衡，出现数据倾斜
    //如果想让数据均衡，可以进行shuffle处理

    //val newRDD: RDD[Int] = rdd.coalesce(2)
    val newRDD: RDD[Int] = rdd.coalesce(2,true)

    newRDD.saveAsTextFile("output")

    sc.stop()
  }
}
