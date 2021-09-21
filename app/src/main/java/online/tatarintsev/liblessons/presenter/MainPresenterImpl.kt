package online.tatarintsev.liblessons.presenter

import online.tatarintsev.liblessons.view.MainView
import android.os.Bundle
import online.tatarintsev.liblessons.model.CountersModel

const val SAVE_COUNTERS: String = "SAVE_COUNTERS"

class MainPresenterImpl(private val countersModel: CountersModel): MainPresenter {


    private var view: MainView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        savedInstanceState?.getIntArray(SAVE_COUNTERS)?.let {
            countersModel.setAll(it.toList())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val intArray = countersModel.getAll().toIntArray()
        if(intArray.isNotEmpty()) {
            outState.putIntArray(SAVE_COUNTERS, intArray)
        }
    }

    override fun onAttachView(view: MainView) {
        this.view = view
        refreshCountersView()
    }

    override fun onDetachView() {
        view = null
    }

    /**
     * метод обрабатывает действия пользователя, в нашем случае клик по конпке, связанной со счетчиком
     * увличивает счетчик на диницу и передает в кнопку новое значние для изображения
     * либо бросает исключение
     * @param - номер счетчика
     */
    override fun onUserAction(count: Int) {
         val newValue = when(count){
            0 -> {
                countersModel.next(0)
            }
            1 -> {
                countersModel.next(1)
            }
            2 -> {
                countersModel.next(2)
            }
            else -> throw Exception("Unknown element")
        }
        view?.updateCounter(count, newValue)
    }

    private fun refreshCountersView() {
        val list = countersModel.getAll()
        for(i in list.indices) {
            view?.updateCounter(i, list[i])
        }
    }

}
