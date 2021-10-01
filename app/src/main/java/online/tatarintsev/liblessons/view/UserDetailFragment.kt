package online.tatarintsev.liblessons.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import online.tatarintsev.liblessons.App
import online.tatarintsev.liblessons.databinding.UserDetailBinding
import online.tatarintsev.liblessons.presenter.UserDetailPresenter


class UserDetailFragment: MvpAppCompatFragment(), UserDetailView, BackButtonListener {
    companion object {
        fun newInstance() = UserDetailFragment()
    }

    val presenter: UserDetailPresenter by moxyPresenter { UserDetailPresenter(App.instance.router) }

    private var vb: UserDetailBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        UserDetailBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }


    override fun init() {

    }


    override fun backPressed() = presenter.backPressed()
}