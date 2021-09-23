package online.tatarintsev.liblessons.model

interface CountersModel {
    fun get(index: Int): Int

    fun next(index: Int): Int

    fun set(index: Int, value: Int)

}