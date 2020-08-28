package com.example.office.adapter;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.office.R;
import com.example.office.data.Url_sheet;
import com.example.office.mcontext.Mycontext;

import org.litepal.LitePal;

import java.util.List;


public class Baozhang_adapter extends RecyclerView.Adapter<Baozhang_adapter.myholder> {

    private int ids = -1;

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    private List<Url_sheet> myurl_list;

    public Baozhang_adapter(List<Url_sheet> myurl_list) {
        this.myurl_list = myurl_list;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(Mycontext.getcontext()).inflate(R.layout.baozang_item,parent,false);

        myholder myholders = new myholder(view);

        return myholders;
    }

    @Override
    public void onBindViewHolder(@NonNull final myholder holder, final int position) {

        holder.textView.setText(myurl_list.get(position).getName());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnclickinyerfaces!=null){
                    btnclickinyerfaces.btnlinster(myurl_list.get(position).getId(),view);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
               setIds(holder.getAdapterPosition());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myurl_list.size();
    }



    static class myholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private TextView textView;
        private Button btn;
        public myholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.url_name);
            btn = itemView.findViewById(R.id.jingru_url);
            itemView.setOnCreateContextMenuListener(this);

        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            contextMenu.add(Menu.NONE,6, Menu.NONE,"修改");
            contextMenu.add(Menu.NONE,7,Menu.NONE,"删除");
            contextMenu.add(Menu.NONE,8,Menu.NONE,"zoom_test");
        }
    }
    public interface btnclickinyerface{
        void btnlinster(int id,View view);
    }
    private btnclickinyerface btnclickinyerfaces=null;
    public void setbtnclick(btnclickinyerface btnclickinyerface){
        this.btnclickinyerfaces=btnclickinyerface;
    }
    public void refesh(List<Url_sheet> url_sheets){
        this.myurl_list = url_sheets;
        notifyDataSetChanged();
    }

    @Override
    public void onViewRecycled(@NonNull myholder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }
    public void removeItem(int post){
        int id = myurl_list.get(post).getId();
        LitePal.delete(Url_sheet.class,id);
        List<Url_sheet> url_sheets = LitePal.findAll(Url_sheet.class);
        this.myurl_list = url_sheets;
        notifyDataSetChanged();

    }
    public int getsuip_id(int post){
        int id  = myurl_list.get(post).getId();
        return id;
    }

}
