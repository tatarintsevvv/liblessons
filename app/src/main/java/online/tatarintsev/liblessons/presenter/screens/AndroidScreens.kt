package online.tatarintsev.liblessons.presenter.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import online.tatarintsev.liblessons.view.UserDetailFragment
import online.tatarintsev.liblessons.view.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}