package com.hunter95.spark.core.rdd.operator.action

import org.apache.spark.{SparkConf, SparkContext}

object Spark07_RDD_Operator_Action {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4))

    val user = new User

    //RDD算子中传递的函数是会包含闭包操作，那么就会进行检测功能
    //闭包检测
    rdd.foreach(num => println("age=" + (user.age + num)))

    sc.stop()
  }

  //样例类在编译时，会自动混入序列化特质(实现可序列化接口)
  //class User extends Serializable {
  class User() {
    var age: Int = 30
  }
}
