package com.user.javaPainter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    MyView theView;
    private final String PERMISSION_WRITE_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theView = findViewById(R.id.theView);
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

    }

    public void clear(View view) {
        theView.clear();
    }

    public void undo(View view) {
        theView.undo();
    }
    public void savepicture(View view) {
        if (!hasPermission()) {
            if (needCheckPermission()) {
                //如果須要檢查權限，由於這個步驟要等待使用者確認，
                //所以不能立即執行儲存的動作，
                //必須在 onRequestPermissionsResult 回應中才執行
                return;
            }
        }else{
            Log.e("aaa", "savepicture");
            theView.savepicture(theView);
        }
    }
    private boolean needCheckPermission() {
        //MarshMallow(API-23)之後要在 Runtime 詢問權限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] perms = {PERMISSION_WRITE_STORAGE};
            int permsRequestCode = 200;
            requestPermissions(perms, permsRequestCode);
            return true;
        }

        return false;
    }
    /**
     * 是否已經請求過該權限
     * API < 23 一律回傳 true
     */
    private boolean hasPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return(ActivityCompat.checkSelfPermission(this, PERMISSION_WRITE_STORAGE) == PackageManager.PERMISSION_GRANTED);
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200){
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(">>>", "取得授權，可以執行動作了");
                }
            }
        }
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

    public void share(View v){
        savepicture(v);
        File file = theView.share();
        // invoke an intent with ACTION_SEND
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

}
