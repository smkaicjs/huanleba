package com.example.office.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.office.R;
import com.example.office.adapter.Baozhang_adapter;
import com.example.office.data.Url_sheet;
import com.example.office.main.BaseActivity;

import org.litepal.LitePal;

public class Edit_url extends BaseActivity implements View.OnClickListener{

    private Button btn;
    private EditText name,url;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_url);
        btn = findViewById(R.id.edit_commi);
        btn.setOnClickListener(this);


        EditText name = findViewById(R.id.edit_name);
        EditText url = findViewById(R.id.edit_url);
        Intent intent = getIntent();
        id = intent.getIntExtra("suipingid",0);
        Url_sheet urlSheet = LitePal.find(Url_sheet.class,id);
        name.setText(urlSheet.getName());
        url.setText(urlSheet.getUrl());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit_commi:


                ContentValues values = new ContentValues();


                String names = String.valueOf(name.getText());
                values.put("name",names);

                String urls = String.valueOf(url.getText());
                values.put("url",urls);
                LitePal.update(Url_sheet.class,values,id);
                finish();
                break;
        }

    }
}