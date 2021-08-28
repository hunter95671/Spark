package com.hunter95.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming06_State_Window1 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))
    ssc.checkpoint("cp")

    val lines: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    val wordToOne: DStream[(String, Int)] = lines.map((_, 1))

    //reduceByKeyAndWindow：当窗口范围比较大，但滑动幅度比较小
    // 那么可以采用增加数据和删除数据的方式，无需重复计算，提升性能
    val windowDS: DStream[(String, Int)] =
      wordToOne.reduceByKeyAndWindow(
        (x: Int, y: Int) => x + y,
        (x: Int, y: Int) => x - y,
        Seconds(6), Seconds(3)
      )

    val wordToCount: DStream[(String, Int)] = windowDS.reduceByKey(_ + _)

    wordToCount.print()

    //启动采集器
    ssc.start()

    //等待采集器的关闭
    ssc.awaitTermination()
  }
}
