package online.tatarintsev.liblessons.presenter

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import moxy.MvpPresenter
import online.tatarintsev.liblessons.entity.GithubUser
import online.tatarintsev.liblessons.presenter.adapter.UserItemView
import online.tatarintsev.liblessons.presenter.screens.IScreens
import online.tatarintsev.liblessons.repository.GithubUsersRepo
import online.tatarintsev.liblessons.view.UserDetailFragment
import online.tatarintsev.liblessons.view.UsersView

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(FragmentScreen { UserDetailFragment.newInstance() })
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
