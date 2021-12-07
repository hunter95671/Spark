package com.hunter95.spark

import com.hunter95.spark.sql.Spark01_SparkSQL_Basic.User
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object HomeWork04 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()

    import spark.implicits._
    val df1 = spark.sparkContext.textFile("datas/employee.txt")
      .map( x => x.split(","))
      .map( x => Employee(x(0).toInt,x(1),x(2).toInt)).toDF()

    df1.createOrReplaceTempView("employee")
    val employeeRDD = spark.sql("select id,name,age from employee")
    employeeRDD.map(t => "id:"+t(0)+","+"name:"+t(1)+","+"age:"+t(2)).show()
  }
  case class Employee(id:Int,name:String,age:Int)
}