package com.hunter95.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object Spark02_SparkSQL_UDF {
  def main(args: Array[String]): Unit = {

    //创建SparkSQL的运行环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._

    val df: DataFrame = spark.read.json("datas/user.json")
    df.createOrReplaceTempView("user")

    //自定义UDF函数
    //User-Defined-Function 自定义函数 、一进一出；
    spark.udf.register("prefixName",(name:String)=>"Name:"+name)

    spark.sql("select age,prefixName(username) from user").show()

    //关闭环境
    spark.close()
  }
}
