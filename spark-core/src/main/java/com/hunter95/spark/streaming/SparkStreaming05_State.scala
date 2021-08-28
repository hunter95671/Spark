package com.hunter95.spark.streaming

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming05_State {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    //使用有状态操作时，需要设置检查点路径
    ssc.checkpoint("cp")

    //无状态数据操作，只对当前的采集周期内的数据进行处理
    //在某些场合下，需要保留数据统计结果(状态)，实现数据的汇总
    val datas: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    val wordToOne: DStream[(String, Int)] = datas.map((_, 1))

    //val wordToCount: DStream[(String, Int)] = wordToOne.reduceByKey(_ + _)
    //updateStateByKey：根据Key对数据的状态进行更新
    //传递的参数中有两个值
    //第一个值表示相同的Key的Value数据
    //第二个值表示缓冲区相同Key的Value数据
    val state = wordToOne.updateStateByKey(
      (seq: Seq[Int], buff: Option[Int]) => {
        val newCount: Int = buff.getOrElse(0) + seq.sum
        Option(newCount)
      }
    )

    state.print()

    //启动采集器
    ssc.start()

    //等待采集器的关闭
    ssc.awaitTermination()
  }
}
