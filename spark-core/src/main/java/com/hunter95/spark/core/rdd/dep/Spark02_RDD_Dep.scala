package com.hunter95.spark.core.rdd.dep

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_Dep {

  def main(args: Array[String]): Unit = {

    val sparConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparConf)

    val lines: RDD[String] = sc.textFile("datas/word.txt")

    // OneToOneDependency 新的RDD的一个分区的数据依赖于旧的RDD的一个分区 (窄依赖)
    // ShuffleDependency 新的RDD的一个分区的数据依赖于旧的RDD的多个分区  (宽依赖)

    //打印相邻两个RDD的依赖关系
    println(lines.dependencies)
    println("=================")

    val words: RDD[String] = lines.flatMap(_.split(" "))

    //打印相邻两个RDD的依赖关系
    println(words.dependencies)
    println("=================")

    val wordGroup: RDD[(String, Iterable[String])] = words.groupBy(word => word)

    //打印相邻两个RDD的依赖关系
    println(wordGroup.dependencies)
    println("=================")

    val wordToCount = wordGroup.map({
      case (word, list) => (word, list.size)
    })

    //打印相邻两个RDD的依赖关系
    println(wordToCount.dependencies)
    println("=================")

    val array: Array[(String, Int)] = wordToCount.collect()
    array.foreach(println)

    sc.stop()

  }
}
