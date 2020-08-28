package com.example.office.main;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.office.R;
import com.example.office.activity.Registered;
import com.example.office.activity.User_firstpage;
import com.example.office.data.User_Sheet;
import com.example.office.mcontext.Mycontext;

import org.litepal.LitePal;

import java.util.List;
import java.util.zip.Inflater;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private String name,password;
    private LinearLayout linview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater li  = this.getLayoutInflater();
        linview = (LinearLayout) li.inflate(R.layout.activity_main,null);
        setContentView(linview);
        Log.d("时间戳", String.valueOf(System.currentTimeMillis()));
        Button btn = findViewById(R.id.user_login);
        TextView zhuce = findViewById(R.id.user_zhuce);
        btn.setOnClickListener(this);
        zhuce.setOnClickListener(this);
        //
        createadmin_user();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_login:
                if (data_login()){
                    Toast.makeText(Mycontext.getcontext(),"登录成功！！！",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Mycontext.getcontext(), User_firstpage.class);
                    startActivity(intent);

                    //

                    createadmin_user();

                    break;
                }
                else {
                    Toast.makeText(Mycontext.getcontext(),"我怀疑你在盗用主人的手机",Toast.LENGTH_SHORT).show();

                }

                break;


            case R.id.user_zhuce:
                Intent intent = new Intent(MainActivity.this, Registered.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
    private boolean data_login(){
        EditText names = findViewById(R.id.user_name);
        EditText passwords = findViewById(R.id.user_password);

        name = String.valueOf(names.getText());
        password = String.valueOf(passwords.getText());
        List<User_Sheet> user = LitePal.findAll(User_Sheet.class);
        for (User_Sheet userpeople:user){
            String na = userpeople.getName().toString();

            if (na.equals(name)&&userpeople.getPassword().toString().equals(password)){

                return true;
            }

        }
        return false;
    }
    private int getuserId(){
        EditText names = findViewById(R.id.user_name);
        EditText passwords = findViewById(R.id.user_password);
        name = String.valueOf(names.getText());
        password = String.valueOf(passwords.getText());
        List<User_Sheet> user = LitePal.findAll(User_Sheet.class);
        for (User_Sheet userpeople:user){
            String na = userpeople.getName().toString();

            if (na.equals(name)&&userpeople.getPassword().toString().equals(password)){
                Log.d("首页用户id为", String.valueOf(userpeople.getId()));

                return userpeople.getId();
            }

        }
        return -1;

    }
    private void createadmin_user (){

        if (LitePal.find(User_Sheet.class,1)==null&&adminpanduan()){
            User_Sheet admin = new User_Sheet();
            admin.setName(getuserId()+"");
            admin.setPassword("admins");
            admin.save();
        }
        else {
            ContentValues values = new ContentValues();
            values.put("name",getuserId()+"");
            LitePal.update(User_Sheet.class,values,adminpanduan_id());
        }
    }
    private boolean adminpanduan(){
        List<User_Sheet> userSheets = LitePal.findAll(User_Sheet.class);
        for (User_Sheet admin :userSheets){
            if (admin.getPassword().equals("admins")){

                return false;
            }
        }
        return true;
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



}