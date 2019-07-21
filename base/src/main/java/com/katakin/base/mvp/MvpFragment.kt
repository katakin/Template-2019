package com.katakin.base.mvp

import android.os.Bundle
import android.view.View
import com.katakin.base.di.DI
import com.katakin.base.ui.BaseFragment
import toothpick.Scope
import toothpick.Toothpick
import java.util.*

private const val SCOPE_NAME_TAG = "SCOPE_NAME_TAG"
private const val PRESENTER_TAG = "PRESENTER_TAG"

abstract class MvpFragment<V : IView, P : MvpPresenter<V>> : BaseFragment(), IView {

    private var isAttach: Boolean = false

    private lateinit var scopeName: String
    protected open val parentScopeName: String by lazy {
        (parentFragment as? MvpFragment<*,*>)?.scopeName ?: DI.APP_SCOPE
    }
    protected lateinit var scope: Scope
        private set


    private lateinit var presenterKey: String
    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        initToothpick(savedInstanceState)
        super.onCreate(savedInstanceState)
        initPresenter(savedInstanceState)
    }

    private fun initToothpick(savedInstanceState: Bundle?) {
        scopeName = savedInstanceState?.getString(SCOPE_NAME_TAG) ?: "${javaClass::getSimpleName}-${hashCode()}"
        if (Toothpick.isScopeOpen(scopeName)) {
            scope = Toothpick.openScope(scopeName)
        } else {
            scope = Toothpick.openScopes(parentScopeName, scopeName)
            installModules(scope)
        }
        Toothpick.inject(this, scope)
    }

    protected open fun installModules(scope: Scope) {}

    private fun initPresenter(savedInstanceState: Bundle?) {
        presenterKey = savedInstanceState?.getString(PRESENTER_TAG) ?: UUID.randomUUID().toString()
        presenter = PresenterStore.get(presenterKey, this::providePresenter)
    }

    protected abstract fun providePresenter(): P

    override fun onStart() {
        super.onStart()
        attachView()
    }

    override fun onBackPressed(): Boolean {
        return presenter.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SCOPE_NAME_TAG, scopeName)
        outState.putString(PRESENTER_TAG, presenterKey)
        detachView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        detachView()
        if (isFinishing()) {
            presenter.onDestroy()
            PresenterStore.remove(presenterKey)
            Toothpick.closeScope(scope.name)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun attachView() {
        if (!isAttach) {
            isAttach = true
            presenter.attachView(this as V)
        }
    }

    private fun detachView() {
        if (isAttach) {
            isAttach = false
            presenter.detachView()
        }
    }

    private var lastClickTime: Long = 0L
    fun View.setOnClickListenerWithThrottle(
        delay: Long = 500L,
        action: () -> Unit
    ) {
        setOnClickListener {
            val currentTime = System.currentTimeMillis()
            val delta = currentTime - lastClickTime
            if (delta !in 0L..delay) {
                lastClickTime = currentTime
                action.invoke()
            }
        }
    }
}