package testing

import scala.collection.mutable

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
                                          case Some(value) =>
                                            true
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


  // tests
  println(s"The values are: ${twoSum(nums = Array(2,7,11,15), target=9).mkString(",")}")
  println(s"The values are: ${twoSum(nums = Array(3,2,4), target=6).mkString(",")}")

  println(s"The values are: ${twoSum2(nums = Array(2,7,11,15), target=9).mkString(",")}")
  println(s"The values are: ${twoSum2(nums = Array(3,2,4), target=6).mkString(",")}")
  println(s"The values are: ${twoSum2(nums = Array(3,3), target=6).mkString(",")}")
}