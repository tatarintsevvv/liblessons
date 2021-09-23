package online.tatarintsev.liblessons.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import online.tatarintsev.liblessons.view.UserDetailView

class UserDetailPresenter(val router: Router) : MvpPresenter<UserDetailView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    fun loadData() {
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
