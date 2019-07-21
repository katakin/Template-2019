package com.katakin.template.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.katakin.template.Screens

class MainAdapter(fragmentManger: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManger, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Screens.First.fragment
            1 -> Screens.Second.fragment
            2 -> Screens.Third.fragment
            else -> Screens.First.fragment
        }
    }

}