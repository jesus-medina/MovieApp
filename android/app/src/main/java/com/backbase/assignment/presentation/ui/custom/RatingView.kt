package com.backbase.assignment.presentation.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.lang.IllegalArgumentException

class RatingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var backgroundPaint: Paint = Paint().apply {
        color = Color.parseColor("#263238")
    }

    private var yellowRingPaint: Paint = Paint().apply {
        color = Color.parseColor("#FFEB3B")
        style = Paint.Style.STROKE
    }

    private var yellowRingBackgroundPaint: Paint = Paint().apply {
        color = Color.parseColor("#30FFEB3B")
        style = Paint.Style.STROKE
    }

    var rating: Int = 0
        set(value) {
            if (value !in 0..100) throw IllegalArgumentException()
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {
            val middleWidth = (width / 2).toFloat()
            val middleHeight = (height / 2).toFloat()
            drawCircle(middleWidth, middleHeight, middleWidth, backgroundPaint)

            val ratingIndicatorWidth = middleWidth * 0.05f
            yellowRingBackgroundPaint.strokeWidth = ratingIndicatorWidth
            drawCircle(
                middleWidth,
                middleHeight,
                middleWidth - ratingIndicatorWidth * 2,
                yellowRingBackgroundPaint
            )

            yellowRingPaint.strokeWidth = ratingIndicatorWidth
            yellowRingPaint.strokeCap = Paint.Cap.ROUND
            drawArc(
                ratingIndicatorWidth * 2,
                middleHeight - middleWidth + ratingIndicatorWidth * 2,
                width.toFloat() - ratingIndicatorWidth * 2,
                middleHeight + middleWidth - ratingIndicatorWidth * 2,
                -90f,
                360f / 100 * rating,
                false,
                yellowRingPaint
            )
        }
    }
}