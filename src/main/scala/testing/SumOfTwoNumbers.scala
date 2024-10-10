package testing

import scala.annotation.tailrec
import scala.collection.mutable
import scala.math.abs

object SumOfTwoNumbers extends App {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    nums.zipWithIndex flatMap {
      case (e, i) => nums.zipWithIndex.drop(i + 1).collect {
        case (ee, ii) if e + ee == target => Array(i, ii)
      }
    }
  }.headOption match {
    case Some(v) => v
    case None => Array()
  }

  def twoSum2(nums: Array[Int], target: Int): Array[Int] = {
    val numDict = mutable.LinkedHashMap(nums.zipWithIndex.toSeq: _*)

    if (numDict.size == nums.length)
      nums.zipWithIndex find {
        case (e, i) => {
          numDict.drop(i+1).get(target - e) match {
                                          case Some(value) => true
                                          case _ => false
          }
        }
        case _ => false
      }
       match {
        case Some(value) =>
          Array(value._2, numDict.getOrElse(target - value._1, 0))
        case None => Array()
      }
    else
      twoSum(nums, target)
  }

  def twoSum3(nums: Array[Int], target: Int): Array[Int] = {
    var mp = Map[Int, Int]()

    val is_found = (v: Int) => {
      if (mp.getOrElse(v, -1) != -1) true else false
    }

    @tailrec
    def find(ls: List[(Int, Int)]): List[Int] =
      ls match {
        case Nil => List()
        case head :: tail if is_found(head._1) => List(head._2, mp.getOrElse(head._1, -1))
        case head :: tail =>
          val rest = abs(target - head._1)
          mp = mp + (rest -> head._2)
          find(tail)
      }

    find(nums.zipWithIndex.toList).sorted.toArray
  }


  // tests
//  println(s"The values are: ${twoSum(nums = Array(2,7,11,15), target=9).mkString(",")}")
//  println(s"The values are: ${twoSum(nums = Array(3,2,4), target=6).mkString(",")}")
//
//  println(s"The values are: ${twoSum2(nums = Array(2,7,11,15), target=9).mkString(",")}")
//  println(s"The values are: ${twoSum2(nums = Array(3,2,4), target=6).mkString(",")}")
//  println(s"The values are: ${twoSum2(nums = Array(3,3), target=6).mkString(",")}")

  println(s"The values are: ${twoSum3(nums = Array(2,7,11,15), target=9).mkString(",")}")
  println(s"The values are: ${twoSum3(nums = Array(3,2,4), target=6).mkString(",")}")
  println(s"The values are: ${twoSum3(nums = Array(3,3), target=6).mkString(",")}")
}