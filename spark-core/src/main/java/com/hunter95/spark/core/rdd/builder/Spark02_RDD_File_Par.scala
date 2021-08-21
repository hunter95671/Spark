package com.hunter95.spark.core.rdd.builder

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_File_Par {

  def main(args: Array[String]): Unit = {

    //1.准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //2.创建RDD
    //textFile将文件作为数据处理的数据源
    //minPartitions最小分区数量
    //math.min(defaultParallelism, 2)
    //如果不想使用默认的分区数量，可用通过第二个参数指定分区数
    //Spark读取文件，底层其实使用的是Hadoop的读取方式
    //分区数量的计算方式：
    // totalSize = 7
    // goalSize = 7/2 = 3(byte)
    // 7/3 = 2 .... +1 = 3分区

    val rdd = sc.textFile("datas/1.txt", 2)

    rdd.saveAsTextFile("output")

    //3.关闭环境
    sc.stop()
  }
}
