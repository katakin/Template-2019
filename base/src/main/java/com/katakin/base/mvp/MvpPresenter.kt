package com.katakin.base.mvp

import java.lang.ref.WeakReference

abstract class MvpPresenter<V: IView> : IPresenter<V> {

    private var isFirstLaunch = true
    private var viewRef: WeakReference<V>? = null
    private val viewCommands = mutableListOf<ViewCommand<V>>()

    protected val view: V? get() = viewRef?.get()

    override fun attachView(view: V) {
        this.viewRef = WeakReference(view)
        if (isFirstLaunch) {
            isFirstLaunch = false
            onFirstViewAttach()
        }
        applyViewCommands(view)
    }

    protected open fun onFirstViewAttach() {}

    private fun applyViewCommands(view: V) {
        val iterator = viewCommands.iterator()

        while (iterator.hasNext()) {
            val command = iterator.next()
            command.apply(view)

            if (command.tag == null) {
                iterator.remove()
            }
        }
    }

    override fun detachView() {
        viewRef?.clear()
        viewRef = null
    }

    override fun onDestroy() {
        viewCommands.clear()
    }

    open fun onBackPressed(): Boolean = false

    protected fun viewState(commandTag: ViewCommandTag? = null, action: (V) -> Unit) {
        view?.let { action.invoke(it) }

        if (view == null || commandTag != null) {
            addViewCommand(commandTag, action)
        }
    }

    private fun addViewCommand(commandTag: ViewCommandTag?, action: (V) -> Unit) {
        if (commandTag != null) {
            val iterator = viewCommands.iterator()

            while (iterator.hasNext()) {
                val command = iterator.next()
                if (command.tag == commandTag) {
                    iterator.remove()
                    break
                }
            }
        }

        val command = object : ViewCommand<V>(commandTag) {
            override fun apply(view: V) {
                action.invoke(view)
            }
        }
        viewCommands.add(command)
    }
}