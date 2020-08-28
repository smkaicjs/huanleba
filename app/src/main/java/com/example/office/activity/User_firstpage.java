package com.example.office.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.office.R;
import com.example.office.data.Url_sheet;
import com.example.office.frag.First_page_frag;
import com.example.office.frag.Myhome_page_frag;
import com.example.office.frag.Mess_page_frag;
import com.example.office.main.BaseActivity;
import com.example.office.main.Exitapp;
import com.example.office.mcontext.Mycontext;

import org.litepal.LitePal;

import java.util.List;

public class User_firstpage extends BaseActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3;
    private FragmentManager manager = getSupportFragmentManager();

    private Mess_page_frag messPageFrag;

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_firstpage);

        frameLayout = findViewById(R.id.frame_page);

        btn1 = findViewById(R.id.page_1);
        btn2 = findViewById(R.id.page_2);
        btn3 = findViewById(R.id.page_3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.user_custom3);

        actionBar.setDisplayShowCustomEnabled(true);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(User_firstpage.this, new String[]{Manifest.permission.INTERNET,
                    Manifest.permission.READ_CONTACTS}, 1);
        }
//        http://403.workarea3.live/index.php
        String url_last = getSharedPreferences("LASTURL",MODE_PRIVATE).getString("URL","https:www.baidu.com");
        First_page_frag pageFrag = new First_page_frag(url_last);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_page, pageFrag);
        transaction.commit();


        //
        Url_sheet urlSheet = new Url_sheet();
        urlSheet.setUrl("http://403.workarea3.live/index.php");
        urlSheet.setName("基础版本");
        if (sql_data(urlSheet)) {
            urlSheet.save();
        }
        Url_sheet urlSheet1 = new Url_sheet();
        urlSheet1.setName("spp");
        urlSheet1.setUrl("http://spp.senbafu.cn/index.php?k=%E7%9B%B4%E6%92%AD&p=35");
        if (sql_data(urlSheet1)) {
            urlSheet1.save();
        }
        Url_sheet urlSheet2 = new Url_sheet();
        urlSheet2.setName("基础导航");
        urlSheet2.setUrl("https://as.91xsp.us/18web");
        if (sql_data(urlSheet2)) {
            urlSheet2.save();
        }
        Url_sheet jiuse = new Url_sheet();
        jiuse.setUrl("http:9se2.xyz");
        jiuse.setName("酒色清纯");
        if (sql_data(jiuse)) {
            jiuse.save();
        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.page_1:
                btn1.setBackgroundColor(getResources().getColor(R.color.darkkhaki));
                btn2.setBackgroundColor(getResources().getColor(R.color.darkslateblue));
                btn3.setBackgroundColor(getResources().getColor(R.color.darkslateblue));
                String laturl = getSharedPreferences("LASTURL",MODE_PRIVATE).getString("URL","https://www.baidu.com");
                First_page_frag frag = new First_page_frag(laturl);

                FragmentTransaction transaction2 = manager.beginTransaction();
                transaction2.replace(R.id.frame_page, frag);
                transaction2.addToBackStack(null);
                transaction2.commit();
                ActionBar actionBars = getSupportActionBar();


                actionBars.setCustomView(R.layout.user_custom3);
                actionBars.setDisplayShowCustomEnabled(true);


                break;

            case R.id.page_2:
                btn2.setBackgroundColor(getResources().getColor(R.color.darkkhaki));
                btn1.setBackgroundColor(getResources().getColor(R.color.darkslateblue));
                btn3.setBackgroundColor(getResources().getColor(R.color.darkslateblue));
                messPageFrag = new Mess_page_frag();

                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.replace(R.id.frame_page, messPageFrag);
                transaction1.addToBackStack(null);
                transaction1.commit();

//                LocationManager gps = (LocationManager) this.getSystemService(LOCATION_SERVICE);
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    ActivityCompat.requestPermissions(User_firstpage.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
//
//                }
//                Location location = gps.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                Log.d(TAG, location.toString());
                ActionBar actionBar1 = getSupportActionBar();
                actionBar1.setCustomView(R.layout.user_custom2);
                break;
            case R.id.page_3:
                btn3.setBackgroundColor(getResources().getColor(R.color.darkkhaki));
                btn1.setBackgroundColor(getResources().getColor(R.color.darkslateblue));
                btn2.setBackgroundColor(getResources().getColor(R.color.darkslateblue));

                FragmentTransaction transaction3 = manager.beginTransaction();
                transaction3.addToBackStack(null);
                Myhome_page_frag myhomePageFrag = new Myhome_page_frag();
                transaction3.replace(R.id.frame_page,myhomePageFrag);
                transaction3.commit();
                ActionBar actionBar4 = getSupportActionBar();
                actionBar4.setCustomView(R.layout.user_custom);
                actionBar4.setDisplayShowCustomEnabled(true);

                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"你做了一个明智的决定",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this,"你将不能使用此功能",Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long exitTime;
    private void exit() {

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再次点击退出应用", Toast.LENGTH_SHORT).show();

            exitTime = System.currentTimeMillis();
        } else {

//            Intent intent = new Intent();
//            setResult(4,intent);
            Exitapp.removeallactivity();
        }

    }
    private boolean sql_data(Url_sheet url_sheet){
        List<Url_sheet> url_sheets = LitePal.findAll(Url_sheet.class);
        for (Url_sheet url:url_sheets){
            if (url_sheet.getName().equals(url.getName())){
                return false;
            }
        }

        return true;
    }





}