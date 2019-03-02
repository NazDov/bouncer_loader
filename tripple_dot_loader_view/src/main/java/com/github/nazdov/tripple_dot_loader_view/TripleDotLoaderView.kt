package com.github.nazdov.tripple_dot_loader_view

import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.REVERSE
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View

class TripleDotLoaderView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        const val DEF_VIEW_WIDTH: Int = 200
        const val DEF_VIEW_HEIGHT: Int = 100
        const val DEF_CUSTOM_COLOR_CODE: Int = 0xFFB0B0B0.toInt()
        const val DEF_STATE_LOADING_COLOR: Int = Color.GREEN
        const val DEF_FIRST_DOT_OFFSET_X_DP: Int = 50
        const val DEF_SECOND_DOT_OFFSET_X_DP: Int = 110
        const val DEF_THIRD_DOT_OFFSET_X_DP: Int = 170
        const val DEF_DOT_RADIUS_DP: Int = 10
    }

    private var firstDotPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var secondDotPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var thirdDotPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val loaderComputation: BouncerLoaderComputation =
        BouncerLoaderComputation(resources.displayMetrics.density.toDouble())
    private var mediumViewHeight: Int = 0
    private var firstDotOffsetX: Float
    private val secondDotOffsetX: Float
    private var thirdDotOffsetX: Float
    private var firstDotOffsetY: Float = 0.0f
    private var secondDotOffsetY: Float = 0.0f
    private var thirdDotOffsetY: Float = 0.0f
    private var dotRadius: Float
    @ColorInt
    private var customDotColor: Int
    @ColorInt
    private var stateLoadingColor: Int = DEF_STATE_LOADING_COLOR

    init {
        firstDotOffsetX = loaderComputation.dpToPX(DEF_FIRST_DOT_OFFSET_X_DP).toFloat()
        secondDotOffsetX = loaderComputation.dpToPX(DEF_SECOND_DOT_OFFSET_X_DP).toFloat()
        thirdDotOffsetX = loaderComputation.dpToPX(DEF_THIRD_DOT_OFFSET_X_DP).toFloat()
        dotRadius = loaderComputation.dpToPX(DEF_DOT_RADIUS_DP).toFloat()

        val typedArray = context
            ?.obtainStyledAttributes(attrs, R.styleable.TripleDotLoaderView)

        customDotColor = typedArray?.getColor(
            R.styleable.TripleDotLoaderView_dotColor,
            DEF_CUSTOM_COLOR_CODE
        ) ?: DEF_CUSTOM_COLOR_CODE

        stateLoadingColor = typedArray?.getColor(
            R.styleable.TripleDotLoaderView_stateLoadingColor,
            DEF_STATE_LOADING_COLOR
        ) ?: DEF_STATE_LOADING_COLOR

        firstDotPaint.color = customDotColor
        secondDotPaint.color = customDotColor
        thirdDotPaint.color = customDotColor

        animateCircleBouncer()

        typedArray?.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            loaderComputation.dpToPX(DEF_VIEW_WIDTH).toInt(),
            loaderComputation.dpToPX(DEF_VIEW_HEIGHT).toInt()
        )

        mediumViewHeight = loaderComputation.dpToPX(DEF_VIEW_HEIGHT / 2).toInt()

        firstDotOffsetY = mediumViewHeight.toFloat()
        secondDotOffsetY = mediumViewHeight.toFloat()
        thirdDotOffsetY = mediumViewHeight.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)

        canvas?.drawCircle(
            firstDotOffsetX, firstDotOffsetY, dotRadius, firstDotPaint
        )

        canvas?.drawCircle(
            secondDotOffsetX, secondDotOffsetY, dotRadius, secondDotPaint
        )

        canvas?.drawCircle(
            thirdDotOffsetX, thirdDotOffsetY, dotRadius, thirdDotPaint
        )
    }

    private fun animateCircleBouncer() {

        val xAnimValueRange = 1f
        val yAnimValueRange = 1.5f
        val zAnimValueRange = 2f
        val animationDuration: Long = 500

        val firstCircleAnimDelay: Long = 0
        val secondCircleAnimDelay: Long = 200
        val thirdCircleAnimDelay: Long = 500

        createAndStartCircleAnimation(
            xAnimValueRange, yAnimValueRange, zAnimValueRange, animationDuration,
            firstCircleAnimDelay
        )
        {
            val stateY = it.animatedValue as Float

            firstDotOffsetY = mediumViewHeight.toFloat() / stateY

            setLoadingStateColor(stateY, firstDotPaint)

            invalidate()
        }

        createAndStartCircleAnimation(
            xAnimValueRange,
            yAnimValueRange,
            zAnimValueRange, animationDuration, secondCircleAnimDelay
        ) {
            val stateY = it.animatedValue as Float

            secondDotOffsetY = mediumViewHeight.toFloat() / stateY

            setLoadingStateColor(stateY, secondDotPaint)

            invalidate()
        }

        createAndStartCircleAnimation(
            xAnimValueRange,
            yAnimValueRange,
            zAnimValueRange,
            animationDuration, thirdCircleAnimDelay
        ) {
            val stateY = it.animatedValue as Float

            thirdDotOffsetY = mediumViewHeight.toFloat() / stateY

            setLoadingStateColor(stateY, thirdDotPaint)

            invalidate()
        }
    }

    private fun setLoadingStateColor(state: Float, paint: Paint) {
        when (state) {
            in 1.0f..1.5f -> {
                paint.color = stateLoadingColor
            }
            else -> {
                paint.color = customDotColor
            }
        }
    }

    private fun createAndStartCircleAnimation(
        xRange: Float,
        yRange: Float,
        zRange: Float,
        animationDuration: Long,
        animDelay: Long,
        animatorUpdateListener: (ValueAnimator) -> Unit
    ) {
        ValueAnimator.ofFloat(xRange, yRange, zRange).apply {
            duration = animationDuration
            repeatMode = REVERSE
            repeatCount = INFINITE
            startDelay = animDelay
            addUpdateListener(animatorUpdateListener)
        }.start()
    }

    @Suppress("unused")
    fun setCustomDotColor(@ColorInt color: Int) {
        this.customDotColor = color
        firstDotPaint.color = color
        secondDotPaint.color = color
        thirdDotPaint.color = color
    }

    @Suppress("unused")
    fun setLoadingStateColor(@ColorInt color: Int) {
        this.stateLoadingColor = color
    }

}