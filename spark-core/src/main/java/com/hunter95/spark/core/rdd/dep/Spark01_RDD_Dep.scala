package com.hunter95.spark.core.rdd.dep

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Spark01_RDD_Dep {

  def main(args: Array[String]): Unit = {

    val sparConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparConf)

    val lines: RDD[String] = sc.textFile("datas/word.txt")

    //打印血缘关系
    println(lines.toDebugString)
    println("=================")

    val words: RDD[String] = lines.flatMap(_.split(" "))

    //打印血缘关系
    println(words.toDebugString)
    println("=================")

    val wordGroup: RDD[(String, Iterable[String])] = words.groupBy(word => word)

    //打印血缘关系
    println(wordGroup.toDebugString)
    println("=================")

    val wordToCount = wordGroup.map({
      case (word, list) => (word, list.size)
    })

    //打印血缘关系
    println(wordToCount.toDebugString)
    println("=================")

    val array: Array[(String, Int)] = wordToCount.collect()
    array.foreach(println)

    sc.stop()

  }
}
