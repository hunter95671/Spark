package com.hunter95.spark.core.framework.service

import com.hunter95.spark.core.framework.common.TService
import com.hunter95.spark.core.framework.dao.WordCountDao
import org.apache.spark.rdd.RDD

/**
 * 服务层
 */
class WordCountService extends TService{

  private val wordCountDao = new WordCountDao()

  //数据分析
  def dataAnalysis() = {

    val lines = wordCountDao.readFile("datas/word.txt")

    val words: RDD[String] = lines.flatMap(_.split(" "))

    val wordToOne = words.map(
      word => (word, 1)
    )
    val wordGroup: RDD[(String, Iterable[(String, Int)])] = wordToOne.groupBy(
      tuple => tuple._1
    )

    val wordToCount = wordGroup.map({
      case (word, list) => {
        list.reduce(
          (t1, t2) => {
            (t1._1, t1._2 + t2._2)
          }
        )
      }
    })

    val array: Array[(String, Int)] = wordToCount.collect()
    array
  }
}