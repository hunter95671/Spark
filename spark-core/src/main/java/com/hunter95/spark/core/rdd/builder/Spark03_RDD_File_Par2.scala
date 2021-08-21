package com.hunter95.spark.core.rdd.builder

import org.apache.spark.{SparkConf, SparkContext}

object Spark03_RDD_File_Par2 {

  def main(args: Array[String]): Unit = {

    //1.准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //2.创建RDD

    //数据分区的分配

    //14 byte / 2 = 7 byte
    //14/7=2分区
    /*
      1234567@@  => 0 1 2 3 4 5 6 7 8
      89@@       => 9 10 11 12
      0          => 13

      [0,7]      => 1234567
      [7,14]     => 890
     */

    //如果数据源为多个文件，那么计算分区时以文件为单位进行分区
    val rdd = sc.textFile("datas/word.txt", 2)

    rdd.saveAsTextFile("output")

    //3.关闭环境
    sc.stop()
  }
}
