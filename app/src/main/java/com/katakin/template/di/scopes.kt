package com.katakin.template.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FirstScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SecondScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ThirdScope