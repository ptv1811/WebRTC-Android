package com.example.webrtcandroid.base

import androidx.lifecycle.ViewModel
import com.example.webrtcandroid.utils.preference.PreferencesHelper
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {
    @Inject
    lateinit var preferencesHelper: PreferencesHelper
}