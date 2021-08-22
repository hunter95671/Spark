package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object Spark15_RDD_Operator_Transform {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - Key - Value类型算子
    //reduceByKey 相同Key聚合

    val rdd = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3), ("b", 1)))

    //reduceByKey：相同的Key的数据进行Value数据的聚合操作
    //scala中一般的聚合操作都是两两聚合，spark中也一样
    //reduceByKey参数为Value的聚合方法
    val reduceRDD: RDD[(String, Int)] = rdd.reduceByKey(_ + _)

    reduceRDD.collect().foreach(println)

    sc.stop()
  }
}
