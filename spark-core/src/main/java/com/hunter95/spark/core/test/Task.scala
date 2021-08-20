package com.hunter95.spark.core.test

class Task extends Serializable {

  val datas = List(1, 2, 3, 4)
  val logic = (num: Int) => {num * 2}


}
