package edu.knoldus

import util.control.Breaks._

class Sorting {

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
    }
    val sortedList = arrList.toList
    sortedList
  }

  def selectionSort(list: List[Int]): List[Int] = {
    val arrList = list.toArray
    for (i <- 0 until arrList.length-1) {
      var min = i
      for (j <- i+1 until arrList.length){
        if(arrList(j) < arrList(min))
          min = j
      }
      val temp = arrList(min)
      arrList(min) = arrList(i)
      arrList(i) = temp
    }
    val sortedList = arrList.toList
    sortedList
  }

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

}
