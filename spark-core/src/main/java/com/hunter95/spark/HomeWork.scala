package com.hunter95.spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object HomeWork {
  def main(args: Array[String]): Unit = {

    //创建SparkSQL的运行环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()

    //执行逻辑操作

    //DataFrame
    val df: DataFrame = spark.read.json("datas/employee.json")
    //df.show()

    //DataFrame => SQL
    df.createOrReplaceTempView("employee")

    //spark.sql("select * from employee").show()
    //spark.sql("select distinct(*) from employee").show()
    //spark.sql("select age,name from employee").show()
    //spark.sql("select * from employee where age>30").show()
    //spark.sql("select collect_set(name),age from employee group by age").show()
    //spark.sql("select * from employee order by name ").show()
    //spark.sql("select * from employee limit 3").show()
    //spark.sql("select name username from employee").show()
    //spark.sql("select avg(age) from employee").show()
    spark.sql("select min(age) from employee").show()

    //关闭环境
    spark.close()
  }
}
