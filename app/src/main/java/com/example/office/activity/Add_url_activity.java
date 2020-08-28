package com.example.office.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.office.R;
import com.example.office.data.Url_sheet;
import com.example.office.frag.Mess_page_frag;
import com.example.office.main.BaseActivity;
import com.example.office.mcontext.Mycontext;

public class Add_url_activity extends BaseActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_url_activity);
        Button btn = findViewById(R.id.add_commit);
        btn.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.add_commit:
                EditText editText1 = findViewById(R.id.add_url_name);
                String name = String.valueOf(editText1.getText());
                EditText editText2 = findViewById(R.id.add_url_url);
                String url = String.valueOf(editText2.getText());
                Url_sheet add_url = new Url_sheet();
                add_url.setName(name);
                add_url.setUrl(url);
                add_url.save();

                Toast.makeText(this,"Save Succ",Toast.LENGTH_SHORT).show();
                setResult(2);

                finish();

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}