package com.android.devtest.presentation.helper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.android.devtest.R

class CastomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private lateinit var mPaint: Paint
    private lateinit var paintText: Paint
    private lateinit var pathLine: Path
    private val fist = "Fist"
    private val secont = "Secont"
    private val third = "Third"
    private val fourth = "Fourth"

    /* ------------------------------------------------ Линия Оси Х  ------------------------------------------------- */

    private fun linePatch(): Path {
        paintText = Paint(Paint.ANTI_ALIAS_FLAG)
        paintText.strokeWidth = 2F
        paintText.textSize = 35F
        pathLine = Path()
        pathLine.moveTo((width.toFloat() / 8), height.toFloat() / 2)
        pathLine.lineTo(width.toFloat() - (width.toFloat() / 8), height.toFloat() / 2)
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10F
        mPaint.color = Color.RED

        return pathLine
    }

    private fun points2(): Path {
        pathLine = Path()
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
        mPaint.color = ContextCompat.getColor(context, R.color.third_color)
        pathLine.addCircle((width.toFloat() / 8), height.toFloat() / 2, 25F, Path.Direction.CW)
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
        return pathLine
    }

    private fun points1(): Path {
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
        mPaint.color = ContextCompat.getColor(context, R.color.fist_color)
        pathLine = Path()
        pathLine.addCircle(
            (width.toFloat() / 2) - (width.toFloat() / 8),
            height.toFloat() / 2,
            25F,
            Path.Direction.CW
        )

        return pathLine
    }


    private fun points3(): Path {
        pathLine = Path()
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
        mPaint.color = ContextCompat.getColor(context, R.color.secont_color)
        pathLine.addCircle(
            width.toFloat() - (width.toFloat() / 8),
            height.toFloat() / 2,
            25F,
            Path.Direction.CW
        )
        return pathLine
    }

    private fun points4(): Path {
        pathLine = Path()
        pathLine.addCircle(
            (width.toFloat() / 2) + (width.toFloat() / 8),
            height.toFloat() / 2,
            25F,
            Path.Direction.CW
        )
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
        mPaint.color = ContextCompat.getColor(context, R.color.fourth_color)
        return pathLine
    }

    /* ------------------------------------------------ Text ------------------------------------------------- */
    private fun text1(): Path {
        pathLine = Path()
        pathLine.moveTo(
            (width.toFloat() / 2) - (width.toFloat() / 8) - 25F,
            (height.toFloat() / 2) + 55F
        )
        pathLine.lineTo(
            (width.toFloat() / 2) - (width.toFloat() / 8) + 25F,
            (height.toFloat() / 2) + 55F
        )

        return pathLine
    }

    private fun text2(): Path {
        pathLine = Path()
        pathLine.moveTo((width.toFloat() / 8) - 55F, (height.toFloat() / 2) + 55F)
        pathLine.lineTo((width.toFloat() / 8) + 55F, (height.toFloat() / 2) + 55F)

        return pathLine
    }

    private fun text3(): Path {
        pathLine = Path()
        pathLine.moveTo(width.toFloat() - (width.toFloat() / 8) - 45F, (height.toFloat() / 2) + 55F)
        pathLine.lineTo(width.toFloat() - (width.toFloat() / 8) + 45F, (height.toFloat() / 2) + 55F)

        return pathLine
    }

    private fun text4(): Path {

        pathLine = Path()
        pathLine.moveTo(
            (width.toFloat() / 2) + (width.toFloat() / 8) - 55F,
            (height.toFloat() / 2) + 55F
        )
        pathLine.lineTo(
            (width.toFloat() / 2) + (width.toFloat() / 8) + 55F,
            (height.toFloat() / 2) + 55F
        )

        return pathLine
    }

    /* ------------------------------------------------ Canvas ------------------------------------------------- */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint = Paint()
        mPaint.style = Paint.Style.FILL
        mPaint.color = Color.WHITE
        canvas.drawPaint(mPaint)
        linePatch()
        canvas.drawPath(linePatch(), mPaint)
        linePatch().close()
        canvas.apply {
            drawPath(points1(), mPaint)
            drawTextOnPath(fist, text1(), 0F, 0F, paintText)
            drawPath(points2(), mPaint)
            drawTextOnPath(secont, text2(), 0F, 0F, paintText)
            drawPath(points3(), mPaint)
            drawTextOnPath(third, text3(), 0F, 0F, paintText)
            drawPath(points4(), mPaint)
            drawTextOnPath(fourth, text4(), 0F, 0F, paintText)
        }
        invalidate()
    }
}