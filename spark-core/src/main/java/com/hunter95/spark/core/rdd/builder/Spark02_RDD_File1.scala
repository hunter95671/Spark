package com.hunter95.spark.core.rdd.builder

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_File1 {

  def main(args: Array[String]): Unit = {

    //1.准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //2.创建RDD
    //从文件中创建RDD，将文件中的数据作为处理的数据源

    //textFile ：以行为单位读取数据，读取的数据都是字符串
    //wholeTextFiles ：以文件为单位读取数据，结果是元组（文件路径，内容）
    val rdd: RDD[(String, String)] = sc.wholeTextFiles("datas")

    rdd.collect().foreach(println)

    //3.关闭环境
    sc.stop()
  }
}
