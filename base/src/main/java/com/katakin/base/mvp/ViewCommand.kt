package com.katakin.base.mvp

internal abstract class ViewCommand<V : IView>(val tag: ViewCommandTag?) {
    abstract fun apply(view: V)
}