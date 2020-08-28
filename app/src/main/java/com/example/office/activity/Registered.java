package com.example.office.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.office.R;
import com.example.office.data.User_Sheet;
import com.example.office.main.BaseActivity;
import com.example.office.mcontext.Mycontext;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Registered extends BaseActivity implements View.OnClickListener{
    private String names,password_first,password_secend,invs;
    public static final String myinv="smkzuode";
    private static final String Tag ="测试";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registered that = (Registered) o;
        return names.equals(that.names) &&
                password_first.equals(that.password_first) &&
                password_secend.equals(that.password_secend) &&
                invs.equals(that.invs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names, password_first, password_secend, invs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        Button btn = findViewById(R.id.zhece_btn);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        EditText name = findViewById(R.id.zhuce_name);
        EditText password1 = findViewById(R.id.zhuce_password1);
        EditText password2 = findViewById(R.id.zhuce_password2);
        EditText inv = findViewById(R.id.invoit_number);
        names = String.valueOf(name.getText());
        password_first = String.valueOf(password1.getText());
        password_secend = String.valueOf(password2.getText());
        invs = String.valueOf(inv.getText());
        switch (view.getId()){
            case R.id.zhece_btn:
                List<User_Sheet> user_sheets = LitePal.findAll(User_Sheet.class);
                for (User_Sheet user:user_sheets){
                    if (!user.getName().equals(names)){
                        if (myinv.equals(invs)){
                            if (password_first.equals(password_secend)&&!password_first.isEmpty()&&password_first.length()>=8&&names.length()>=6){
                                User_Sheet new_user = new User_Sheet();
                                new_user.setName(names);
                                new_user.setDate(new Date().getTime());
                                new_user.setPassword(password_first);
                                new_user.save();
                                Intent intent = new Intent(Mycontext.getcontext(),Tiaozhuan.class);
                                startActivity(intent);
                                finish();
                                break;

                            }
                            else{

                                Toast.makeText(Mycontext.getcontext(),"主人您输入的密码不一致哦,或者密码没有8位哦,或者账号没有6位哦",Toast.LENGTH_SHORT).show();
                                break;


                            }
                        }else{
                            Toast.makeText(Mycontext.getcontext(),"主人您的邀请码不正确呐...请联系辣个男人把！",Toast.LENGTH_SHORT).show();
                            break;

                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}