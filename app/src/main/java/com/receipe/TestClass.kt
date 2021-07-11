package com.receipe

import android.util.Log

class TestClass {

    val TAG = "TestClass"

    val name: String
        inline get() = "name"

    init {
        var result = 5 minus 8
        Log.e(TAG, result.toString())

        result = increment(7, 6, 1, 198)
        Log.e(TAG, result.toString())

        val list = getListOfIntsEvenToThree()
        Log.e(TAG, list.toString())

        list.forEach { }

        Log.e(TAG, calculate(1).toString())

        `12kf`()
    }

}

infix fun Int.minus(int: Int): Int = this - int

fun increment(vararg ints: Int): Int {
    var result = 0;
    ints.forEach { result += it }
    return result
}

//локальные функции
fun getListOfIntsEvenToThree(): List<Int> {
    fun getMutableListOfInt(): MutableList<Int> {
        var list: MutableList<Int> = mutableListOf()
        for (i in 0..100) {
            list.add(i)
        }
        return list
    }
    return getMutableListOfInt().filter { it % 3 == 0 }
}

//быстрое решение задачи, основанное на циклах
tailrec fun calculate(int: Int = 5): Int =
    if (int == 5) int else if (int < 5) calculate(int + 1) else calculate(int - 1)

fun `12kf` (){
}

