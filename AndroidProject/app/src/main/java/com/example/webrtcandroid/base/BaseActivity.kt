package com.example.webrtcandroid.base

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.webrtcandroid.R
import com.example.webrtcandroid.utils.preference.PreferencesHelper
import javax.inject.Inject

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {
    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutId) as DB
    }

    abstract val layoutId: Int

    abstract val setViewModel: BaseViewModel?

    abstract fun setDataBinding(): Boolean

    abstract fun initView()

    abstract fun detachView()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        if (setDataBinding()) {
            binding.lifecycleOwner = this
            binding.executePendingBindings()
        } else {
            setContentView(layoutId)
        }
        initView()
    }

    fun addFragment(fragment: Fragment, addBackStack: String? = null) {
        supportFragmentManager.commit {
            addToBackStack(addBackStack)
            add(R.id.layout_container, fragment)
        }
    }

    fun addFragmentToStack(fragment: BaseFragment<ViewDataBinding>) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .add(R.id.layout_container, fragment)
            .addToBackStack(null)
            .commit()
        supportFragmentManager.executePendingTransactions()
    }

    override fun onDestroy() {
        super.onDestroy()
        detachView()
    }
    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }
}