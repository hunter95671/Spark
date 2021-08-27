package com.hunter95.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.expressions.Aggregator

object Spark04_SparkSQL_JDBC {
  def main(args: Array[String]): Unit = {

    //创建SparkSQL的运行环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._

    //读取MySQL数据
    val df = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/springboot")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "oooppp999666")
      .option("dbtable", "book")
      .load()

    df.show()

    //保存数据
    df.write
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/springboot")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "oooppp999666")
      .option("dbtable", "book1")
      .mode(SaveMode.Append)
      .save()
    //关闭环境
    spark.close()
  }
}
