package com.android.springanimation.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.springanimation.R

class SimpleBounceViewDemoActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, SimpleBounceViewDemoActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_bounce_view_demo)
    }
}