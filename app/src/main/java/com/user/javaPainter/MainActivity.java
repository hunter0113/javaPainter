package com.user.javaPainter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MyView theView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theView = findViewById(R.id.theView);


    }

    public void clear(View view) {
        theView.clear();
    }

    public void undo(View view) {
        theView.undo();
    }

    public void ColorClick(View view) {
        switch (view.getId()){
            case R.id.btblack:
                theView.setColor("black");
                break;
            case R.id.btblue:
                theView.setColor("blue");
                break;
            case R.id.btbrown:
                theView.setColor("brown");
                break;
            case R.id.btgray:
                theView.setColor("gray");
                break;
            case R.id.btgreap:
                theView.setColor("greap");
                break;
            case R.id.btgreen:
                theView.setColor("green");
                break;
            case R.id.btlightblue:
                theView.setColor("lightblue");
                break;
            case R.id.btorange:
                theView.setColor("orange");
                break;
            case R.id.btpink:
                theView.setColor("pink");
                break;
            case R.id.btred:
                theView.setColor("red");
                break;
            case R.id.btwhite:
                theView.setColor("white");
                break;
            case R.id.btyellow:
                theView.setColor("yellow");
                break;
        }
    }
}
