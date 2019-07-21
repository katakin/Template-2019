package com.katakin.template.ui.first

import com.katakin.base.mvp.MvpFragment
import com.katakin.template.R
import com.katakin.template.presentation.first.FirstPresenter
import com.katakin.template.presentation.first.FirstView
import javax.inject.Inject
import javax.inject.Provider

class FirstFragment : MvpFragment<FirstView, FirstPresenter>() {

    @Inject
    lateinit var daggerPresenter: Provider<FirstPresenter>

    override fun providePresenter(): FirstPresenter = daggerPresenter.get()

    override val layoutRes: Int get() = R.layout.fragment_first
}