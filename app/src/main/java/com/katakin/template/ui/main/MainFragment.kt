package com.katakin.template.ui.main

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.katakin.base.mvp.MvpFragment
import com.katakin.base.ui.BaseFragment
import com.katakin.template.R
import com.katakin.template.presentation.main.MainPresenter
import com.katakin.template.presentation.main.MainView
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject
import javax.inject.Provider

class MainFragment : MvpFragment<MainView, MainPresenter>() {

    @Inject
    lateinit var daggerPresenter: Provider<MainPresenter>

    override fun providePresenter(): MainPresenter = daggerPresenter.get()

    override val layoutRes: Int get() = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        main_bottomnav.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.first_tab -> {
                    main_viewpager.setCurrentItem(0, true)
                    true
                }
                R.id.second_tab -> {
                    main_viewpager.setCurrentItem(1, true)
                    true
                }
                R.id.third_tab -> {
                    main_viewpager.setCurrentItem(2, true)
                    true
                }
                else -> {
                    false
                }
            }
        }

        with(main_viewpager) {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = MainAdapter(childFragmentManager, lifecycle)
            registerOnPageChangeCallback(
                object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        when (position) {
                            0 -> main_bottomnav.selectedItemId = R.id.first_tab
                            1 -> main_bottomnav.selectedItemId = R.id.second_tab
                            2 -> main_bottomnav.selectedItemId = R.id.third_tab
                        }
                    }
                }
            )
        }
    }

    override fun onBackPressed(): Boolean {
        return currentTabFragment?.onBackPressed() ?: super.onBackPressed()
    }

    private val currentTabFragment: BaseFragment?
        get() = childFragmentManager.fragments.firstOrNull { !it.isHidden } as? BaseFragment
}