package com.example.office.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.office.R;

public class zoomActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);

        Button button = findViewById(R.id.user_zoom);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ImageView iv = findViewById(R.id.zoomimage);
        switch (view.getId()){
            case R.id.user_zoom:{
                ViewGroup.LayoutParams lp = iv.getLayoutParams();
                DisplayMetrics dm = new DisplayMetrics();
                WindowManager wm = getWindowManager();
                this.getWindowManager().getDefaultDisplay().getRealMetrics(dm);

                Log.d("屏幕参数", "ydg----------------"+dm.toString());
                Log.d("屏幕参数", "ydg----------------"+dm.widthPixels);
                lp.height = 500;
                lp.width = 500;
                iv.setLayoutParams(lp);
                break;
            }
        }
    }
}