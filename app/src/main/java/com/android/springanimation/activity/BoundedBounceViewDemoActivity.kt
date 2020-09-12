package com.android.springanimation.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.springanimation.R
import kotlinx.android.synthetic.main.activity_bounded_bounce_view_demo.*

class BoundedBounceViewDemoActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, BoundedBounceViewDemoActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bounded_bounce_view_demo)
        bounceView.setUpBounds(containerLayout)
    }
}