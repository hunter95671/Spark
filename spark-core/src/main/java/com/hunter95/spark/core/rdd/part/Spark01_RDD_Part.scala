package com.hunter95.spark.core.rdd.part

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object Spark01_RDD_Part {
  def main(args: Array[String]): Unit = {
    val sparConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparConf)

    val rdd = sc.makeRDD(List(
      ("nba", "xxxxxxxxxxxxxxx"),
      ("cba", "xxxxxxxxxxxxxxx"),
      ("wnba", "xxxxxxxxxxxxxxx")
    ))

    val partRDD: RDD[(String, String)] = rdd.partitionBy(new MyPartitioner)

    partRDD.saveAsTextFile("output")

    sc.stop()
  }

  /**
   * 自定义分区器
   * 1.继承Partitioner
   * 2.重写方法
   */
  class MyPartitioner extends Partitioner {

    //分区数量
    override def numPartitions: Int = 3

    //返回数据的分区索引(从0开始)
    override def getPartition(key: Any): Int = {
      key match {
        case "nba" => 0
        case "wnba" => 1
        case _ => 2
      }
    }
  }
}
