package com.hunter95.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming07_Output1 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))
    ssc.checkpoint("cp")

    val lines: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    val wordToOne: DStream[(String, Int)] = lines.map((_, 1))


    val windowDS: DStream[(String, Int)] =
      wordToOne.reduceByKeyAndWindow(
        (x: Int, y: Int) => x + y,
        (x: Int, y: Int) => x - y,
        Seconds(6), Seconds(3)
      )

    //foreachRDD不会出现时间戳
    windowDS.foreachRDD(
      rdd=>rdd
    )

    //启动采集器
    ssc.start()

    //等待采集器的关闭
    ssc.awaitTermination()
  }
}
