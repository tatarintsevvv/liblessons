package online.tatarintsev.liblessons.model

object CountersModelImpl: CountersModel {

    private var counters = mutableListOf(0, 0, 0)

    override fun getCurrent(index: Int): Int {
        return counters[index]
    }

    override fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }

    override fun set(index: Int, value: Int){
        counters[index] = value
    }

    override fun getAll(): List<Int> = counters.toList()

    override fun setAll(list: List<Int>) {
        counters = list.toMutableList()
    }
}