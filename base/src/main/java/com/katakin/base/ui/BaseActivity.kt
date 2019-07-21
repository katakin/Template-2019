package com.katakin.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.katakin.base.R

abstract class BaseActivity : AppCompatActivity() {

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.activity_container) as? BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity)
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }
}