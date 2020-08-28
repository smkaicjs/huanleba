package com.example.office.frag;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.office.R;
import com.example.office.activity.Edit_user_data;
import com.example.office.activity.User_list;
import com.example.office.mcontext.Mycontext;

public class Myhome_page_frag extends Fragment implements View.OnClickListener{
    public Myhome_page_frag() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(Mycontext.getcontext()).inflate(R.layout.page3_frag,container,false);
        ImageView imageView = view.findViewById(R.id.tianwang_mima);
        ImageView imageView_user_manager = view.findViewById(R.id.manager_user);
        imageView_user_manager.setOnClickListener(this);
        imageView.setOnClickListener(this);



        return view;
}

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tianwang_mima:
                final EditText editText = new EditText(Mycontext.getcontext());
                editText.setPadding(10,0,0,0);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("进入验证");
                builder.setMessage("主人暗号？？？");
                builder.setView(editText);
                builder.setPositiveButton("敲门", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String anhao = String.valueOf(editText.getText());
                        if (anhao.equals("一群二百五")){
                            Intent intent = new Intent(Mycontext.getcontext(), Edit_user_data.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Mycontext.getcontext(),"你在想什么？？",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("下次，下次", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(Mycontext.getcontext(),"呵呵？？",Toast.LENGTH_SHORT).show();

                    }
                });

                builder.show();
                break;
            case R.id.manager_user:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                final EditText textView = new EditText(Mycontext.getcontext());
                textView.setHint("There enter");
                textView.setAllCaps(false);
                builder1.setView(textView);

                builder1.setMessage("Please enter the superadmin password");
                builder1.setPositiveButton("确认", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String text = String.valueOf(textView.getText());
                                if (text.equals("cjs")){
                                    Intent intent = new Intent(Mycontext.getcontext(), User_list.class);
                                    startActivity(intent);
                                }
                            }
                        });
                builder1.show();
                break;

        }
    }
}
