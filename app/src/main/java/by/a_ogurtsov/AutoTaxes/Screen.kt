package by.a_ogurtsov.AutoTaxes

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class Screen(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var colorLine: Int = 0
    private var paddingLine: Int = 0
    private var angleLine: Int = 0
    private var thicknessLine = 0

    private var paint: Paint = Paint()
    private lateinit var rectF: RectF

    private var mWidth: Int = 0
    private var mHeight: Int = 0


    init {
        var a: TypedArray = context!!.obtainStyledAttributes(
            attrs,
            R.styleable.Screen,
            0, 0
        )
        try {
            this.colorLine = a.getInt(R.styleable.Screen_colorLine, Color.BLUE)
            this.paddingLine = a.getInt(R.styleable.Screen_paddingLine, 20)
            this.angleLine = a.getInt(R.styleable.Screen_angleLine, 20)
            this.thicknessLine = a.getInt(R.styleable.Screen_thicknessLine, 5)
        } finally {
            a.recycle()
        }


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rectF = RectF(paddingLine.toFloat(), paddingLine.toFloat(), (mWidth - paddingLine).toFloat(),
            (mHeight - paddingLine).toFloat())
        paint.color = colorLine
        paint.strokeWidth = thicknessLine.toFloat()
        paint.style = Paint.Style.STROKE

        canvas?.drawRoundRect(this.rectF, this.angleLine.toFloat(), angleLine.toFloat(), paint)
    }
}