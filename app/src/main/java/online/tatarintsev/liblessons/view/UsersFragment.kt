package online.tatarintsev.liblessons.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import online.tatarintsev.liblessons.App
import online.tatarintsev.liblessons.databinding.FragmentUserBinding
import online.tatarintsev.liblessons.presenter.UsersPresenter
import online.tatarintsev.liblessons.presenter.adapter.UsersRVAdapter
import online.tatarintsev.liblessons.presenter.screens.AndroidScreens
import online.tatarintsev.liblessons.repository.GithubUsersRepo

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter { UsersPresenter(GithubUsersRepo(), App.instance.router ) }
    var adapter: UsersRVAdapter? = null

    private var vb: FragmentUserBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }


    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}