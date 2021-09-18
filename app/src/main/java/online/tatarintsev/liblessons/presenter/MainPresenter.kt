package online.tatarintsev.liblessons.presenter

import android.os.Bundle
import online.tatarintsev.liblessons.view.MainView

interface MainPresenter {
    fun onCreate(savedInstanceState: Bundle?)
    fun onSaveInstanceState(outState: Bundle)

    fun onAttachView(view: MainView)
    fun onDetachView()

    fun onUserAction(count: Int)
}