package com.hunter95.spark.core.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_WordCount {

  def main(args: Array[String]): Unit = {

    //建立和spark框架的连接
    val sparConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparConf)

    //执行业务操作

    //1.读取文件，获取一行一行的数据
    val lines: RDD[String] = sc.textFile("datas")

    //2.将一行数据拆分，形成一个一个的单词(分词)
    //扁平化：将整体拆分成个体的操作
    val words: RDD[String] = lines.flatMap(_.split(" "))

    //3.将数据根据单词进行分组，便于统计
    val wordGroup: RDD[(String, Iterable[String])] = words.groupBy(word => word)

    //4.对分组后的数据进行转换
    val wordToCount = wordGroup.map({
      case (word, list) => (word, list.size)
    })

    //5.将转换结果采集到控制台打印出来
    val array:Array[(String,Int)]=wordToCount.collect()
    array.foreach(println)

    //关闭连接
    sc.stop()

  }
}