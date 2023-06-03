package com.example.citiesdistance.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.citiesdistance.R
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus

abstract class BaseFragment : Fragment(), BaseView {
    override val rootView: ConstraintLayout?
        get() = requireView() as ConstraintLayout
    override val viewContext: Context?
        get() = context
}

abstract class BaseActivity : AppCompatActivity(), BaseView {
    override val rootView: ConstraintLayout?
        get() = prepareView()

    private fun prepareView(): ConstraintLayout {
        val viewGroup = window.decorView.findViewById(android.R.id.content) as ViewGroup
        if (viewGroup !is ConstraintLayout) {
            viewGroup.children.forEach {
                if (it is ConstraintLayout) return it
            }
            throw IllegalStateException("RootView must be instance of ConstraintLayout")
        } else {
            return viewGroup
        }
    }

    override val viewContext: Context?
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()

    }
}

interface BaseView {
    val rootView: ConstraintLayout?
    val viewContext: Context?
    fun setProgressIndicator(mustShow: Boolean) {
        rootView?.let {
            var loadingView = it.findViewById<View>(R.id.frameLayout_viewLoading)
            viewContext?.let { context ->
                loadingView = addLoadingView(loadingView, mustShow, context, it)
                loadingView?.visibility = if (mustShow) View.VISIBLE else View.GONE
            }
        }
    }

    fun addLoadingView(
        loadingView: View?, mustShow: Boolean, context: Context, it: ConstraintLayout
    ): View? {
        var loadingView1 = loadingView
        if (loadingView1 == null && mustShow) {
            loadingView1 =
                LayoutInflater.from(context).inflate(R.layout.view_loading, it, false)
            it.addView(loadingView1)
        }
        return loadingView1
    }
}

abstract class BaseViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val progressDialogLiveData = MutableLiveData<Boolean>()
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}