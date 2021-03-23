package com.example.webrtcandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.webrtcandroid.utils.preference.PreferencesHelper
import javax.inject.Inject

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {
    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    abstract val layoutId: Int

    lateinit var binding: VDB

    abstract val setViewModel: BaseViewModel?

    abstract fun setDataBinding(): Boolean

    protected abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (setDataBinding()) {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            binding.lifecycleOwner = this
            binding.root
        } else {
            inflater.inflate(layoutId, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
}