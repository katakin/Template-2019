package com.katakin.base.mvp

internal object PresenterStore {
    private val presenters = HashMap<String, MvpPresenter<out IView>>()

    @Suppress("UNCHECKED_CAST")
    fun <P: MvpPresenter<out IView>>get(key: String, action: () -> P): P {
        return presenters[key] as? P ?: action.invoke().apply {
            presenters[key] = this
        }
    }

    fun remove(key: String) {
        presenters.remove(key)
    }
}