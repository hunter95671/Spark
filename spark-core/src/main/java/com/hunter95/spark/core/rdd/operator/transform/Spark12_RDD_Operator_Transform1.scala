package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark12_RDD_Operator_Transform1 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - sortBy
    //排序
    //第一个参数：依据指定规则来排序
    //第二个参数：默认为升序(true)，降序(false)

    //sortBy默认情况下不改变分区，但是中间存在shuffle操作

    val rdd=sc.makeRDD(List(("1",1),("11",2),("2",3)),2)

    val newRDD: RDD[(String, Int)] = rdd.sortBy(_._2)

    //newRDD.saveAsTextFile("output")
    newRDD.collect().foreach(println)

    sc.stop()
  }
}
