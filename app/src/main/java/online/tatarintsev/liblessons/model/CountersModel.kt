package online.tatarintsev.liblessons.model

interface CountersModel {
    fun getCurrent(index: Int): Int

    fun next(index: Int): Int

    fun set(index: Int, value: Int)

    fun getAll(): List<Int>

    fun setAll(list: List<Int>)
}