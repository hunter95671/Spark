package com.hunter95.spark.core.rdd.builder

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Memory_Par1 {

  def main(args: Array[String]): Unit = {

    //1.准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //2.创建RDD

    //【1,2】 【3,4】
    //val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)

    //【1】 【2】 【3,4】
    //val rdd = sc.makeRDD(List(1, 2, 3, 4), 3)

    //【1】 【2,3】 【4,5】
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5), 3)

    //将处理的数据保存成分区文件
    rdd.saveAsTextFile("output")

    //3.关闭环境
    sc.stop()
  }
}
