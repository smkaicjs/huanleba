package com.example.office.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.office.R;
import com.example.office.adapter.user_adapter;
import com.example.office.data.Url_sheet;
import com.example.office.data.User_Sheet;

import org.litepal.LitePal;

import java.util.List;

public class User_list extends AppCompatActivity {

    private List<User_Sheet> myitem;
    private user_adapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recyclerView = findViewById(R.id.recycler_user_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myitem = LitePal.findAll(User_Sheet.class);
        adapter = new user_adapter(myitem);
        recyclerView.setAdapter(adapter);
        this.registerForContextMenu(recyclerView);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 5:
                adapter.removeItem(adapter.getIds());
                break;

            case 6:
                LitePal.deleteAll(User_Sheet.class);
                break;
        }
        return super.onContextItemSelected(item);
    }
}