package com.user.javaPainter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {

    private LinkedList<LinkedList<HashMap<String,Float>>> lines;
    private Paint paint;


    //context為activity, AttributeSet attrs為更改latout畫面需要
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.WHITE);

        lines = new LinkedList<>();
        paint = new Paint();

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(4);

    }

    //onDraw為自動呼叫
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(LinkedList<HashMap<String,Float>> line : lines){

            for(int i = 1;i<line.size();i++){
                HashMap<String,Float> p0 = line.get(i-1);
                HashMap<String,Float> p1 = line.get(i);
                canvas.drawLine(p0.get("x"),p0.get("y"),p1.get("x"),p1.get("y"),paint);
            }
        }
    }

    //觸發點
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            firstPoint(event);
        }else if(event.getAction()==MotionEvent.ACTION_UP){

        }else if(event.getAction()==MotionEvent.ACTION_MOVE) {
            movePoint(event);
        }

        //需要super.onTouchEvent(event)且return ture  ,setOnClickListener才有效
      //  super.onTouchEvent(event); //有setOnClickListener時為ture，否則為false
        return true;  //持續偵測return true  一次偵測false
    }

    private void firstPoint(MotionEvent event){
        LinkedList<HashMap<String,Float>> line = new LinkedList<>();

        HashMap<String, Float> hashMap = new HashMap<>();
        Float ex = event.getX();
        Float ey = event.getY();
        hashMap.put("x",ex);
        hashMap.put("y",ey);
        line.add(hashMap);

        lines.add(line);
    }
    private void movePoint(MotionEvent event){
        HashMap<String, Float> hashMap = new HashMap<>();
        Float ex = event.getX();
        Float ey = event.getY();
        hashMap.put("x",ex);
        hashMap.put("y",ey);
        lines.getLast().add(hashMap);
        invalidate();
    }

    public void clear(){
        lines.clear();
        invalidate(); //重整畫面
    }

    public void undo(){
        if(lines.size()>0){
            lines.removeLast();
            invalidate(); //重整畫面
        }
    }
    public  void setColor(String color){
        switch (color){
            case "black":
                paint.setColor(Color.parseColor("#000000"));
                break;
            case "blue":
                paint.setColor(Color.parseColor("#8C8C8C"));
                break;
            case "brown":
                paint.setColor(Color.parseColor("#BB5500"));
                break;
            case "gray":
                paint.setColor(Color.parseColor("#888888"));
                break;
            case "greap":
                paint.setColor(Color.parseColor("#7700FF"));
                break;
            case "green":
                paint.setColor(Color.parseColor("#00FF00"));
                break;
            case "lightblue":
                paint.setColor(Color.parseColor("#00FFCC"));
                break;
            case "orange":
                paint.setColor(Color.parseColor("#FF8800"));
                break;
            case "pink":
                paint.setColor(Color.parseColor("#FF44AA"));
                break;
            case "red":
                paint.setColor(Color.parseColor("#FF0000"));
                break;
            case "white":
                paint.setColor(Color.parseColor("#FFFFFF"));
                break;
            case "yellow":
                paint.setColor(Color.parseColor("#FFFF00"));
                break;
        }
    }
}
