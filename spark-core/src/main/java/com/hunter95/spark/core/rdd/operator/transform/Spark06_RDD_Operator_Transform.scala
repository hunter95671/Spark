package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark06_RDD_Operator_Transform {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - groupBy
    //groupBy对数据源中的每一个数据进行分组判断，根据返回的分组Key进行分组
    //相同的Key值的数据会放在一个组中
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)

    val groupRDD: RDD[(Int, Iterable[Int])] = rdd.groupBy(_ % 2)

    groupRDD.collect().foreach(println)

    sc.stop()
  }
}
