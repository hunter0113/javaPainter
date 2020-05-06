package com.user.javaPainter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    private LinkedList<LinkedList<HashMap<String,Float>>> lines;
    private Paint paint;
    private ArrayList<LinkedList<LinkedList<HashMap<String,Float>>>> liness;
    private ArrayList<String> paintColor;
    int chosecolor = 0;
    MyOtherClass myclass;
    FileOutputStream os;
    //context為activity, AttributeSet attrs為更改latout畫面需要
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.WHITE);
        myclass = new MyOtherClass(context);
        lines = new LinkedList<>();
        paint = new Paint();
        liness = new ArrayList<>();
        paintColor = new ArrayList<String>();
/*
        paint.setColor(Color.parseColor("#0000CC"));*/
        paint.setStrokeWidth(10);
    }

    //onDraw為自動呼叫
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLiness(canvas);
    }

    //觸發點
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(choseColor()){
                firstPoint(event);
            }else{
                Toast.makeText(getContext(), "請先選擇顏色", Toast.LENGTH_SHORT).show();
            }
        }else if(event.getAction()==MotionEvent.ACTION_UP){

        }else if(event.getAction()==MotionEvent.ACTION_MOVE) {
            if(choseColor()){
                movePoint(event);
            }
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
        liness.clear();
        paintColor.clear();
        invalidate(); //重整畫面
    }

    public void undo() {
        if (lines.size() > 0) {
            lines.removeLast();
            invalidate(); //重整畫面
        } else if (liness.size() > 0) { //使用前幾種顏色筆跡
            lines = liness.get(liness.size() - 2);
            if (lines.size() > 0) {
                lines.removeLast();
                invalidate(); //重整畫面
            }else{
                if(liness.size() - 3!=-1) {
                    liness.remove(liness.size() - 1);
                    lines = liness.get(liness.size() - 2);
                    if (lines.size() > 0) {
                        lines.removeLast();
                        invalidate(); //重整畫面
                    }
                } else{
                    Toast.makeText(getContext(), "無法再返回", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    public void savelines(){
        lines =  new LinkedList<>();
        liness.add(lines);

        chosecolor=1;
    }

    public boolean choseColor(){
        if(chosecolor!=0){
            return true;
        }else{
            return false;
        }
    }

    public void drawLiness(Canvas canvas){
        if(liness.size()>0){
            //Log.e("arraylist", "liness.size(): "+ liness.size());
           // Log.e("paints", "paint: "+ paintColor.size());
        //有更換過顏色，則
            for (int x=0; x<liness.size();x++){
                lines = liness.get(x);
                paint.setColor(Color.parseColor(paintColor.get(x)));
                for(LinkedList<HashMap<String,Float>> line : lines){
                    for(int i = 1;i<line.size();i++){
                        HashMap<String,Float> p0 = line.get(i-1);
                        HashMap<String,Float> p1 = line.get(i);
                        canvas.drawLine(p0.get("x"),p0.get("y"),p1.get("x"),p1.get("y"),paint);
                    }
                }
            }
        }else{
            //更換顏色之前
            for(LinkedList<HashMap<String,Float>> line : lines){
                for(int i = 1;i<line.size();i++){
                    HashMap<String,Float> p0 = line.get(i-1);
                    HashMap<String,Float> p1 = line.get(i);
                    canvas.drawLine(p0.get("x"),p0.get("y"),p1.get("x"),p1.get("y"),paint);
                }
            }
        }

    }
    public void setColor(String color){
        switch (color){
            case "black":
                paint.setColor(Color.parseColor("#000000"));
                paintColor.add("#000000");
                savelines();
                break;
            case "blue":
                paint.setColor(Color.parseColor("#0000CC"));
                paintColor.add("#0000CC");
                savelines();
                break;
            case "brown":
                paint.setColor(Color.parseColor("#BB5500"));
                paintColor.add("#BB5500");
                savelines();
                break;
            case "gray":
                paint.setColor(Color.parseColor("#888888"));
                paintColor.add("#888888");
                savelines();
                break;
            case "greap":
                paint.setColor(Color.parseColor("#7700FF"));
                paintColor.add("#7700FF");
                savelines();
                break;
            case "green":
                paint.setColor(Color.parseColor("#00FF00"));
                paintColor.add("#00FF00");
                savelines();
                break;
            case "lightblue":
                paint.setColor(Color.parseColor("#77FFEE"));
                paintColor.add("#77FFEE");
                savelines();
                break;
            case "orange":
                paint.setColor(Color.parseColor("#FF8800"));
                paintColor.add("#FF8800");
                savelines();
                break;
            case "pink":
                paint.setColor(Color.parseColor("#FF44AA"));
                paintColor.add("#FF44AA");
                savelines();
                break;
            case "red":
                paint.setColor(Color.parseColor("#FF0000"));
                paintColor.add("#FF0000");
                savelines();
                break;
            case "white":
                paint.setColor(Color.parseColor("#FFFFFF"));
                paintColor.add("#FFFFFF");
                savelines();
                break;
            case "yellow":
                paint.setColor(Color.parseColor("#FFFF00"));
                paintColor.add("#FFFF00");
                savelines();
                break;
        }
    }
    public void savepicture(View v){

        Bitmap bm = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        v.draw(canvas);
// 獲取螢幕
        View dView = ((Activity) getContext()).getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bmp = dView.getDrawingCache();
        if (bmp != null)
        {
            try {
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                //檔案資料夾名稱
                File vrDir = new File(path, "Screenshots");
                if (!vrDir.exists()) {
                    vrDir.mkdirs();
                }

                int r = 0;
                r = (int)(Math.random()*100000)+1;

// 圖片檔案路徑
                File file = new File(vrDir, "a.png");
                try {
                    if(file.exists()){
                        Log.e("aaa", "file存在");
                        file.delete();
                    }
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    os = new FileOutputStream(file);
                }catch (Exception e){

                    Log.e("aaa", e.toString());
                }
                bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
                Log.e("aaa", "存檔完畢");
                myclass.showToast("存檔完畢");

            } catch (Exception e) {
            }
        }else{
            Log.e("aaa", "null");
        }

    }
    public class MyOtherClass {
        private Context context;
        public Toast toast;
        public MyOtherClass(Context context){
            this.context = context;
        }

        private void showToast(String message){
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }

    }
}
