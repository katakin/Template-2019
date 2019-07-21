package com.katakin.template

import com.katakin.template.ui.first.FirstFragment
import com.katakin.template.ui.main.MainFragment
import com.katakin.template.ui.second.SecondFragment
import com.katakin.template.ui.third.ThirdFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object Main : SupportAppScreen() {
        override fun getFragment() = MainFragment()
    }

    object First : SupportAppScreen() {
        override fun getFragment() = FirstFragment()
    }

    object Second : SupportAppScreen() {
        override fun getFragment() = SecondFragment()
    }

    object Third : SupportAppScreen() {
        override fun getFragment() = ThirdFragment()
    }
}