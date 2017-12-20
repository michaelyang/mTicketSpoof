package shmoop.mticket;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class ColorBarView extends View {
    private String date;
    private String time;// TODO: use a default from R.string...
    private int color1;
    private int color2;
    private int color3;// TODO: use a default from R.color...
    private int height;
    private Paint paint;

    public ColorBarView(Context context) {
        super(context);
        init(null, 0);
    }

    public ColorBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ColorBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        paint = new Paint();

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ColorBarView, 0, 0);
        try {
            height = a.getDimensionPixelSize(R.styleable.ColorBarView_height, 0);
            date = a.getString(R.styleable.ColorBarView_date);
            time = a.getString(R.styleable.ColorBarView_time);
            color1 = a.getColor(R.styleable.ColorBarView_color1, 0);
            color2 = a.getColor(R.styleable.ColorBarView_color2, 0);
            color3 = a.getColor(R.styleable.ColorBarView_color3, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int contentWidth = getWidth();
        int contentHeight = height;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();

        paint.setStyle(Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(color1);
        // Draw the text.
        canvas.drawRect(paddingLeft,paddingTop,paddingLeft+contentWidth/3,paddingTop+contentHeight,paint);
        paint.setColor(color2);
        canvas.drawRect(paddingLeft+contentWidth/3,paddingTop,paddingLeft+2*contentWidth/3,paddingTop+contentHeight, paint);
        paint.setColor(color3);
        canvas.drawRect(paddingLeft+2*contentWidth/3,paddingTop,paddingLeft+contentWidth,paddingTop+contentHeight,paint);
    }

    public int getColor1(){
        return color1;
    }
    public int getColor2(){
        return color2;
    }
    public int getColor3(){
        return color3;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
    public void setColor1(int color){
        color1 = color;
        invalidate();
        requestLayout();
    }
    public void setColor2(int color){
        color2 = color;
        invalidate();
        requestLayout();
    }
    public void setColor3(int color){
        color3 = color;
        invalidate();
        requestLayout();
    }
    public void setDate(String date){
        this.date = date;
        invalidate();
        requestLayout();
    }
    public void setTime(String time){
        this.time = time;
        invalidate();
        requestLayout();
    }

    private int measureHeight(int measureSpec) {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        return paddingTop + height + paddingBottom;
    }
    private int measureWidth(int measureSpec) {
        return 800;
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }
}
