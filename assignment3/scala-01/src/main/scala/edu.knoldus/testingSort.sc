import util.control.Breaks._

val arr = List(2,4,3,5,1)

def bubbleSort(list: List[Int]): List[Int] = {
  val arrList = list.toArray
  for (_ <- arrList){
    for (i <- 0 until arrList.length - 1) {
      if (arrList(i + 1) < arrList(i)) {
        val temp = arrList(i + 1)
        arrList(i + 1) = arrList(i)
        arrList(i) = temp
      }
    }
  }
  val sortedList = arrList.toList
  sortedList
}

println(bubbleSort(arr))

def selectionSort(list: List[Int]): List[Int] = {
  val arrList = list.toArray
  for (i <- 0 until arrList.length-1) {
    val minimum = arrList.slice(i, arrList.length).min
    val min = arrList.indexWhere( _ == minimum)
    val temp = arrList(min)
    arrList(min) = arrList(i)
    arrList(i) = temp
  }
  val sortedList = arrList.toList
  sortedList
}

println(selectionSort(arr))

def insertionSort(list: List[Int]): List[Int] = {
  val arrList = list.toArray
  for(i <- 1 until arrList.length){
    val num = arrList(i)
    breakable {
      for (j <- i to 0 by -1) {
        if(j == 0){
          arrList(j) = num
        }
        else if (arrList(j-1) > num) {
          arrList(j) = arrList(j - 1)
        }
        else{
          arrList(j) = num
          break
        }
      }
    }
    /*var pos = i
    while(pos > 0 && num < arrList(pos-1)){
      arrList(pos) = arrList(pos-1)
      pos = pos-1
    }
    arrList(i) = num*/
  }
  val sortedList = arrList.toList
  sortedList
}

println(insertionSort(arr))