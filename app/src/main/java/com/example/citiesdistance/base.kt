package com.example.citiesdistance

abstract class BaseFragment : BaseView

abstract class BaseActivity : BaseView

interface BaseView {
    fun setProgressIndicator(mustShow: Boolean)
}

abstract class BaseViewModel
