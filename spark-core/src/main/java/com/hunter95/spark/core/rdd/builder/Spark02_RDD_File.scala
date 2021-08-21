package com.hunter95.spark.core.rdd.builder

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_File {

  def main(args: Array[String]): Unit = {

    //1.准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    //2.创建RDD
    //从文件中创建RDD，将文件中的数据作为处理的数据源

    // (1)、path路径默认以当前环境的根路径为准。可以写绝对路径，也可以写相对路径
    //sc.textFile("E:\\code\\java\\Spark\\datas\\1.txt")

    //val rdd: RDD[String] = sc.textFile("datas/1.txt")

    // (2)、path路径可以是文件的具体路径，也可以是目录名称，统计目录中所有文件
    //val rdd: RDD[String] = sc.textFile("datas")

    // (3)、path路径还可以使用通配符 *
    //val rdd: RDD[String] = sc.textFile("datas/1*.txt")

    // (4)、path还可以是分布式存储系统路径：HDFS
    val rdd: RDD[String] = sc.textFile("hdfs://hadoop102:8020/test.txt")


    rdd.collect().foreach(println)

    //3.关闭环境
    sc.stop()
  }
}
