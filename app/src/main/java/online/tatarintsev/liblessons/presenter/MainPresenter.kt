package online.tatarintsev.liblessons.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import online.tatarintsev.liblessons.view.MainView
import online.tatarintsev.liblessons.presenter.screens.IScreens

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}