package com.katakin.template.ui.third

import com.katakin.base.mvp.MvpFragment
import com.katakin.template.R
import com.katakin.template.presentation.third.ThirdPresenter
import com.katakin.template.presentation.third.ThirdView
import javax.inject.Inject
import javax.inject.Provider

class ThirdFragment : MvpFragment<ThirdView, ThirdPresenter>() {

    @Inject
    lateinit var daggerPresenter: Provider<ThirdPresenter>

    override fun providePresenter(): ThirdPresenter = daggerPresenter.get()

    override val layoutRes: Int get() = R.layout.fragment_third
}