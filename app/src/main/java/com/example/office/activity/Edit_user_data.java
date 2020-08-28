package com.example.office.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.office.R;
import com.example.office.data.User_Sheet;
import com.example.office.main.BaseActivity;
import com.example.office.mcontext.Mycontext;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;

import com.example.office.service.User_service;

public class Edit_user_data extends BaseActivity implements View.OnClickListener{

    private User_service.mybind mybind;
    public static final String TAG = "天王盖地虎：";
    private EditText editTextname,editTextpass;
    private int USER_ID;
    private Button button_del_user;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_data);
        Button btn = findViewById(R.id.edit_commit);
        btn.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        //
        button_del_user = findViewById(R.id.edit_del_user);
        button_del_user.setOnClickListener(this);
        editTextname = findViewById(R.id.edit_user_name);
        editTextpass = findViewById(R.id.edit_user_pass);
        User_Sheet useradmin = LitePal.find(User_Sheet.class,adminpanduan_id());
        USER_ID = Integer.parseInt(useradmin.getName());
        Log.d(TAG, "用户id:"+USER_ID);
        User_Sheet user_now = LitePal.find(User_Sheet.class,USER_ID);
        editTextpass.setHint(user_now.getPassword());
        editTextname.setHint(user_now.getName());



        TextView user_data = findViewById(R.id.user_data_for_history);
        long time1 = user_now.getDate();
        long time2 =new Date().getTime();
        long timesec =  (time2 - time1)/1000;

        int timemin = (int) (timesec/60);
        int timehour = timemin/60;
//        AlertDialog.Builder builder = getdialog("sj","现在时间："+time2+"注册时间："+time1+"分钟数："
//        +timemin+"小时数："+timehour);
//        builder.setPositiveButton("确认",null);
//
//        builder.show();
        if (timehour>=24){
            int timeday = timehour/24;
            user_data.setText("主人以下是您的报告：\n"+"\b\b  您已经来到本鱼塘"+timeday+
                    "天"+(timehour-timeday*24)+"小时"+(timemin-(timeday*24+timehour-timeday*24)*60)+
                    "分钟了哦，欢迎您的使用与厚爱。希望主人再接再厉。。。");
        }else{
            user_data.setText("主人以下是您的报告：\n"+"\b\b  您已经来到本鱼塘"+timehour+"小时"+(timemin-timehour*60)+
                    "分钟了哦，欢迎您的使用与厚爱。希望主人再接再厉。。。");
        }






    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit_commit:

                if (editTextname.length()>=5&&editTextpass.length()>=8){
                    ContentValues values = new ContentValues();
                    values.put("name", String.valueOf(editTextname));
                    values.put("password", String.valueOf(editTextpass));
                    LitePal.update(User_Sheet.class,values,USER_ID);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("修改成功");
                    builder.setPositiveButton("ok",null);
                    builder.setTitle("mess");
                    builder.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Edit_user_data.this);
                    builder.setTitle("message");
                    TextView textView = new TextView(Mycontext.getcontext());
                    textView.setText("主人您输入的密码或者账号格式有误哦！！");
                    builder.setView(textView);
                    builder.setPositiveButton("确认",null);
                    builder.show();
                }
                break;
            case R.id.edit_del_user:
                AlertDialog.Builder dialog = getdialog("Warring","主人你要去当和尚吗");
                dialog.setPositiveButton("我选择断根为佛", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        LitePal.delete(User_Sheet.class,USER_ID);
                        Intent in = new Intent("com.example.office.QUIT");
                        localBroadcastManager.sendBroadcast(in);



                    }
                });
                dialog.show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    private AlertDialog.Builder getdialog(String title,String content){
        AlertDialog.Builder builder = new AlertDialog.Builder(Edit_user_data.this);
        TextView textView =  new TextView(this);
        textView.setText(content);
        builder.setView(textView);
        builder.setNegativeButton("我选择鱼淫为伍",null);
        return builder;
    }

    private int adminpanduan_id(){
        List<User_Sheet> userSheets = LitePal.findAll(User_Sheet.class);
        for (User_Sheet admin :userSheets){
            if (admin.getPassword().equals("admins")){

                return admin.getId();
            }
        }
        return -1;
    }
}