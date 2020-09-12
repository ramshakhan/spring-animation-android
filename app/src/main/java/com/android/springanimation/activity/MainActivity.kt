package com.android.springanimation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.springanimation.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSimpleBounceView.setOnClickListener {
            startActivity(SimpleBounceViewDemoActivity.newIntent(this))
        }

        buttonBoundedBounceView.setOnClickListener {
            startActivity(BoundedBounceViewDemoActivity.newIntent(this))
        }
    }
}