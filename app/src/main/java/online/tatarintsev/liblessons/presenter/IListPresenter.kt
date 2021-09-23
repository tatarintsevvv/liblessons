package online.tatarintsev.liblessons.presenter

import online.tatarintsev.liblessons.presenter.adapter.IItemView
import online.tatarintsev.liblessons.presenter.adapter.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
