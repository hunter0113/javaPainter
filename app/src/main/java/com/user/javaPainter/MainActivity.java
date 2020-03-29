package com.user.javaPainter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
                theView.setColor("");
                break;
            case R.id.btblue:
                theView.setColor("");
                break;
            case R.id.btbrown:
                theView.setColor("");
                break;
            case R.id.btgray:
                theView.setColor("");
                break;
            case R.id.btgreap:
                theView.setColor("");
                break;
            case R.id.btgreen:
                theView.setColor("");
                break;
            case R.id.btlightblue:
                theView.setColor("");
                break;
            case R.id.btorange:
                theView.setColor("");
                break;
            case R.id.btpink:
                theView.setColor("");
                break;
            case R.id.btred:
                theView.setColor("");
                break;
            case R.id.btwhite:
                theView.setColor("");
                break;
            case R.id.btyellow:
                theView.setColor("");
                break;
        }
    }
}
