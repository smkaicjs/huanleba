package com.example.office.frag;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.office.R;
import com.example.office.activity.Add_url_activity;
import com.example.office.activity.Edit_url;
import com.example.office.activity.zoomActivity;
import com.example.office.adapter.Baozhang_adapter;
import com.example.office.data.Url_sheet;
import com.example.office.main.MainActivity;
import com.example.office.mcontext.Mycontext;

import org.litepal.LitePal;

import java.util.List;

public class Mess_page_frag extends Fragment {


    private RecyclerView recyclerView;
    private Baozhang_adapter adapter;
    public Mess_page_frag() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(Mycontext.getcontext()).inflate(
                R.layout.page2_frag,container,false
        );
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.goldon_respo);
        recyclerView.setLayoutManager(new LinearLayoutManager(Mycontext.getcontext()));
        List<Url_sheet> myitem = LitePal.findAll(Url_sheet.class);
        adapter = new Baozhang_adapter(myitem);
        adapter.setbtnclick(new Baozhang_adapter.btnclickinyerface() {
            @Override
            public void btnlinster(int id, View view) {
                Url_sheet urls = LitePal.find(Url_sheet.class,id);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction1 = manager.beginTransaction();
                First_page_frag first_page_frag = new First_page_frag(urls.getUrl());
                transaction1.replace(R.id.frame_page,first_page_frag);
                transaction1.addToBackStack(null);
                transaction1.commit();
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                ActionBar bar = activity.getSupportActionBar();
                bar.setCustomView(R.layout.user_custom3);
                bar.setDisplayShowCustomEnabled(true);
                Button btn1 = activity.findViewById(R.id.page_1);
                Button btn2 = activity.findViewById(R.id.page_2);
                Button btn3 = activity.findViewById(R.id.page_3);
                btn1.setBackgroundColor(getResources().getColor(R.color.darkkhaki));
                btn2.setBackgroundColor(getResources().getColor(R.color.darkslateblue));
                btn3.setBackgroundColor(getResources().getColor(R.color.darkslateblue));

            }
        });
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.url_page_menu,menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add_url:
                Intent intent = new Intent(Mycontext.getcontext(), Add_url_activity.class);
                startActivityForResult(intent,2);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isgetdata = false;
    @Override
    public void onResume() {
        if (!isgetdata){
            List<Url_sheet> url_sheets = LitePal.findAll(Url_sheet.class);
            adapter.refesh(url_sheets);
        }
        super.onResume();

    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {

            case 7:
                adapter.removeItem(adapter.getIds());
                Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 6:
                int sui_id = adapter.getsuip_id(adapter.getIds());
                Intent intent = new Intent(Mycontext.getcontext(), Edit_url.class);
                intent.putExtra("suipingid",sui_id);
                startActivity(intent);
                Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Intent intent1 = new Intent(Mycontext.getcontext(), zoomActivity.class);
                startActivity(intent1);
                break;
        }

        return super.onContextItemSelected(item);
    }



}
