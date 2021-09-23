package online.tatarintsev.liblessons.model

class CountersModelImpl : CountersModel {

    private var counters = mutableListOf(0, 0, 0)

    override fun get(index: Int): Int {
        return counters[index]
    }

    override fun next(index: Int): Int {
        counters[index]++
        return get(index)
    }

    override fun set(index: Int, value: Int){
        counters[index] = value
    }

}