package as.bwei.com.lianx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HP on 2018/8/2.
 */

public class HuaYuan extends View{

    private Paint mPaint1;

    public HuaYuan(Context context) {
        super(context);
        init();
    }



    public HuaYuan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HuaYuan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public HuaYuan(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init() {
        //创建笔画
        mPaint1 = new Paint();
        //设置画笔颜色为蓝色
        mPaint1.setColor(Color.BLUE);
        //设置画笔宽慰10px
        mPaint1.setStrokeMiter(5f);
        //设置笔画模式为填充
        mPaint1.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取控件的高度
        int width = getWidth();
        int height = getHeight();
        //设置元得半径 = 宽，高最小值的2分之一
        int r = Math.min(width,height)/2;
        //画圆
        canvas.drawCircle(width/2,height/2,r,mPaint1);
    }
}
