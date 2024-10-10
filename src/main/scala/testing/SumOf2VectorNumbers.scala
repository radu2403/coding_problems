package testing


class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x
}

object SumOf2VectorNumbers extends App {


  def addTwoNumbers(l1: ListNode, l2: ListNode, carryOver: Int = 0): ListNode = {
    var res: ListNode = null

    if (l1 != null) {
      if (l2 != null) {
        val nr = l1.x + l2.x + carryOver
        res = new ListNode(_x = nr % 10,
                          _next = addTwoNumbers(l1.next, l2.next, carryOver = nr / 10)
                          )
      } else {
        val nr = l1.x + carryOver
        res = new ListNode(_x = nr % 10,
                           _next = addTwoNumbers(l1.next, null, carryOver = nr / 10)
                          )
      }
    } else {
      if (l2 != null) {
        val nr = l2.x + carryOver
        res = new ListNode(_x = nr % 10,
                           _next = addTwoNumbers(null, l2.next, carryOver = nr / 10)
        )
      } else {
        if (carryOver != 0) {
          res = new ListNode(_x = carryOver,
                             _next = null
          )
        }
      }
    }

    res
  }


  // tests
  val t1_l1 = new ListNode(2, new ListNode(4, new ListNode(3, null)))
  val t1_l2 = new ListNode(5, new ListNode(6, new ListNode(4, null)))
  var res = addTwoNumbers(t1_l1, t1_l2)
  println(s"The values are: ")
  while (res != null){
    print(res.x)
    res = res.next
  }


}
