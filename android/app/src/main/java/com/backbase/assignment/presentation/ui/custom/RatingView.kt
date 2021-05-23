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
    private var textPaint: Paint = Paint().apply {
        color = Color.parseColor("#FFFFFF")
    }

    private var backgroundPaint: Paint = Paint().apply {
        color = Color.parseColor("#263238")
    }

    private var ringPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    private var ringBackgroundPaint: Paint = Paint().apply {
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
            val ringColor = if (rating >= 50) "4CAF50" else "FFEB3B"
            ringPaint.color = Color.parseColor("#$ringColor")
            ringBackgroundPaint.color = Color.parseColor("#30$ringColor")

            val middleWidth = (width / 2).toFloat()
            val middleHeight = (height / 2).toFloat()
            drawCircle(middleWidth, middleHeight, middleWidth, backgroundPaint)

            val ratingIndicatorWidth = middleWidth * 0.05f
            ringBackgroundPaint.strokeWidth = ratingIndicatorWidth
            drawCircle(
                middleWidth,
                middleHeight,
                middleWidth - ratingIndicatorWidth * 2,
                ringBackgroundPaint
            )

            ringPaint.strokeWidth = ratingIndicatorWidth
            drawArc(
                ratingIndicatorWidth * 2,
                middleHeight - middleWidth + ratingIndicatorWidth * 2,
                width.toFloat() - ratingIndicatorWidth * 2,
                middleHeight + middleWidth - ratingIndicatorWidth * 2,
                -90f,
                360f / 100 * rating,
                false,
                ringPaint
            )

            val percentageTextSize = middleWidth * 0.25f

            textPaint.textSize = middleWidth * 0.8f
            textPaint.textAlign = Paint.Align.RIGHT
            val textXPosition = middleWidth * when {
                rating == 100 -> 1.6f
                rating >= 10 -> 1.4f
                else -> 1.2f
            }
            drawText("$rating", textXPosition, middleHeight + percentageTextSize, textPaint)

            textPaint.textSize = percentageTextSize
            textPaint.textAlign = Paint.Align.LEFT
            drawText(
                "%",
                textXPosition,
                middleHeight + percentageTextSize - textPaint.textSize * 1.5f,
                textPaint
            )
        }
    }
}