package com.katakin.base.mvp

interface IPresenter<V: IView> {
    fun attachView(view: V)
    fun detachView()
    fun onDestroy()
}