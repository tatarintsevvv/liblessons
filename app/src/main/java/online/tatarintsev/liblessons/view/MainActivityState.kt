package online.tatarintsev.liblessons.view

import online.tatarintsev.liblessons.model.CountersModelImpl
import online.tatarintsev.liblessons.presenter.MainPresenter
import online.tatarintsev.liblessons.presenter.MainPresenterImpl

class MainActivityState {

    private var presenter: MainPresenter? = null


    fun getPresenter(): MainPresenter {
        if(presenter == null) {
            presenter = createPresenter()


        }
        return presenter as MainPresenter
    }

    private fun createPresenter(): MainPresenter {
        val countersModel = CountersModelImpl
        return MainPresenterImpl(countersModel)
    }

    fun onStart(view: MainView) {
        presenter?.onAttachView(view)
    }

    fun onStop() {
        presenter?.onDetachView()
    }

    fun onDestroy() {
        presenter = null
    }
}