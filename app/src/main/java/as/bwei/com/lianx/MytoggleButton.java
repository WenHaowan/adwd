package as.bwei.com.lianx;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by HP on 2018/8/2.
 */

public class MytoggleButton extends View implements View.OnClickListener{
    private Bitmap switchBackgroud;
    private Bitmap slideButton;
    private Paint paint;
    private float slideBtn_left;

    public MytoggleButton(Context context) {
        super(context);
    }

    public MytoggleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    //初始化
    private void initView() {
        switchBackgroud = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        slideButton = BitmapFactory.decodeResource(getResources(), R.mipmap.b);
        paint = new Paint();//初始化画笔
        paint.setAntiAlias(true);//打开齿轮
        //添加onclick
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //2.设置当前view的大小
        setMeasuredDimension(switchBackgroud.getWidth(),switchBackgroud.getHeight());
    }
    private boolean currstate=false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(switchBackgroud,0,0,paint);
        //绘制可滑动的按钮 左上角值
        canvas.drawBitmap(slideButton,slideBtn_left,0,paint);
    }

    private boolean isDrag=false;

    @Override
    public void onClick(View v) {
            if (!isDrag){
                currstate = !isDrag;
                flushState();
            }
    }

    private void flushState() {
        if (currstate){
            slideBtn_left = switchBackgroud.getWidth() - slideButton.getWidth();
        }else {
            slideBtn_left = 0;
        }
    }
    //down 事件的X值
    private int firstx;
    //touch 事件的上一个X值
    private int lastx;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                firstx = lastx = (int) event.getX();
                isDrag = false;
                break;
            case MotionEvent.ACTION_MOVE:
                //是否发生拖动
                if (Math.abs(event.getX() - firstx) > 5){
                    isDrag = true;
                }
                //计算手指在屏幕上移动的距离
                int dis = (int)(event.getX() - lastx);
                //将本次的位置 设置给lastX
                lastx = (int)event.getX();
                //根据手指移动的距离，改变slideBtn_left 的值
                slideBtn_left = slideBtn_left + dis;
                break;
            case MotionEvent.ACTION_UP:
                //在发生拖动的情况下，根据最后的位置，判断当前开关的状态
                if (isDrag){
                    int maxLeft = switchBackgroud.getWidth() - slideButton.getWidth();
                    /**
                     * 根据 slideBtn_left 判断，当前应是什么状态
                     */
                    if (slideBtn_left > maxLeft/2){//此时应为打开的状态
                        currstate = true;
                    }else {
                        currstate = false;
                    }
                    flushState();
                }
                break;
        }
        flushView();
        return true;
    }

    private void flushView() {
        // slideBtn 左边界最大值
        int maxLeft = switchBackgroud.getWidth() - slideButton.getWidth();
        //确保slideBtn_left >= 0
        slideBtn_left = (slideBtn_left > 0) ? slideBtn_left : 0;
        //确保 slideBtn_left <= maxLeft
        slideBtn_left = (slideBtn_left < maxLeft) ? slideBtn_left : maxLeft;

        invalidate();
    }
}
