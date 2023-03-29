package com.example.citiesdistance

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), BaseView{
    override fun setProgressIndicator(mustShow: Boolean) {
        TODO("Not yet implemented")
    }
}

abstract class BaseActivity : AppCompatActivity(), BaseView{
    override fun setProgressIndicator(mustShow: Boolean) {
        TODO("Not yet implemented")
    }
}

interface BaseView {
    fun setProgressIndicator(mustShow: Boolean)
}

abstract class BaseViewModel
