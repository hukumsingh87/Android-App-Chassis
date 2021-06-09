package com.framework.module.init.controller

import android.os.Bundle
import com.framework.R
import com.framework.application.controller.BaseActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}