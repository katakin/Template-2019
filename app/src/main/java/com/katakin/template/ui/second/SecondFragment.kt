package com.katakin.template.ui.second

import com.katakin.base.mvp.MvpFragment
import com.katakin.template.R
import com.katakin.template.presentation.second.SecondPresenter
import com.katakin.template.presentation.second.SecondView
import javax.inject.Inject
import javax.inject.Provider

class SecondFragment : MvpFragment<SecondView, SecondPresenter>() {

    @Inject
    lateinit var daggerPresenter: Provider<SecondPresenter>

    override fun providePresenter(): SecondPresenter = daggerPresenter.get()

    override val layoutRes: Int get() = R.layout.fragment_second
}