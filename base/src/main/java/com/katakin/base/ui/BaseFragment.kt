package com.katakin.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract val layoutRes: Int

    private var isInSaveState: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        isInSaveState = false
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        isInSaveState = false
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        isInSaveState = true
    }

    open fun onBackPressed(): Boolean = false

    protected fun isFinishing(): Boolean = when {
        activity?.isChangingConfigurations == true -> false
        activity?.isFinishing == true -> true
        else -> isRealRemoving()
    }

    private fun isRealRemoving(): Boolean {
        return (isRemoving && !isInSaveState) || ((parentFragment as? BaseFragment)?.isRealRemoving() ?: false)
    }
}