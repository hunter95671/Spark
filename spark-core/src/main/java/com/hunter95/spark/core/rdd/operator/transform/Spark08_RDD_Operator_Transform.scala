package com.hunter95.spark.core.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

object Spark08_RDD_Operator_Transform {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    //算子 - sample

    val rdd=sc.makeRDD(List(1,2,3,4,5,6,7,8,9,10))

    //sample算子需要传入三个参数
    //第一个参数表示，抽取数据后是否将数据放回 ture(放回),false(丢弃)
    //第二个参数表示：
    //             如果抽取不放回：每条数据被抽取的概率，基准值
    //             如果抽取放回：表示每条数据可能被抽取的次数
    //第三个参数表示，抽取数据时随机算法的种子，不传递的话，那么使用当前系统时间

    println(rdd.sample(false, 0.4,1).collect().mkString(","))
    println(rdd.sample(false, 0.4).collect().mkString(","))
    println(rdd.sample(true, 2).collect().mkString(","))

    sc.stop()
  }
}
