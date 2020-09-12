package com.android.springanimation.view

import android.content.Context
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.android.springanimation.onLayoutReady

class BoundedBounceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val animationX: SpringAnimation by lazy {
        SpringAnimation(this, SpringAnimation.X).apply {
            spring = SpringForce().apply {
                stiffness = SpringForce.STIFFNESS_MEDIUM
                dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            }
        }
    }

    private val animationY: SpringAnimation by lazy {
        SpringAnimation(this, SpringAnimation.Y).apply {
            spring = SpringForce().apply {
                stiffness = SpringForce.STIFFNESS_MEDIUM
                dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            }
        }
    }

    private var deltaX = 0f
    private var deltaY = 0f

    private var translateX = 0f
    private var translateY = 0f

    private lateinit var bounds: RectF

    fun setUpBounds(boundaryLayout: View) {
        boundaryLayout.onLayoutReady {
            bounds = RectF(
                0f,
                0f,
                boundaryLayout.width.toFloat(),
                boundaryLayout.height.toFloat()
            )

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
                    }

                    MotionEvent.ACTION_MOVE -> {
                        translateX = deltaX + event.rawX
                        translateY = deltaY + event.rawY

                        view.x = translateX
                        view.y = translateY
                    }

                    MotionEvent.ACTION_UP -> {
                        //check for X
                        when {
                            translateX > (bounds.centerX()) -> {
                                animationX.animateToFinalPosition(bounds.right - width)
                            }

                            else -> {
                                animationX.animateToFinalPosition(bounds.left)
                            }
                        }

                        //check for Y
                        when {
                            translateY < 0 -> {
                                animationY.animateToFinalPosition(bounds.top)
                            }

                            translateY > (bounds.bottom - height) -> {
                                animationY.animateToFinalPosition(bounds.bottom - height)
                            }

                            else -> {
                                animationY.animateToFinalPosition(translateY)
                            }
                        }
                    }
                }

                return true
            }
        })
    }
}