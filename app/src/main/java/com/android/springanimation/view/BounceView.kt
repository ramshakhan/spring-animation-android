package com.android.springanimation.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.android.springanimation.onLayoutReady

class BounceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var originalX = 0f
    private var originalY = 0f

    private lateinit var animationX: SpringAnimation
    private lateinit var animationY: SpringAnimation

    private var deltaX = 0f
    private var deltaY = 0f

    private var translateX = 0f
    private var translateY = 0f

    init {
        onLayoutReady {
            originalX = x
            originalY = y

            createAnimationX()
            createAnimationY()

            setUpTouchListener()
        }
    }

    //handle dragging the view
    private fun setUpTouchListener() {
        setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        deltaX = view.x - event.rawX
                        deltaY = view.y - event.rawY

                        animationX.cancel()
                        animationY.cancel()
                    }

                    MotionEvent.ACTION_MOVE -> {
                        translateX = deltaX + event.rawX
                        translateY = deltaY + event.rawY

                        view.x = translateX
                        view.y = translateY
                    }

                    MotionEvent.ACTION_UP -> {
                        animationX.start()
                        animationY.start()
                    }
                }

                return true
            }
        })
    }

    private fun createAnimationX() {
        animationX = SpringAnimation(this, SpringAnimation.X).apply {
            spring = SpringForce().apply {
                finalPosition = originalX
                stiffness = SpringForce.STIFFNESS_MEDIUM
                dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            }
        }
    }

    private fun createAnimationY() {
        animationY = SpringAnimation(this, SpringAnimation.Y).apply {
            spring = SpringForce().apply {
                finalPosition = originalY
                stiffness = SpringForce.STIFFNESS_MEDIUM
                dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            }
        }
    }
}