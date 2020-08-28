package com.example.office.adapter;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.office.R;
import com.example.office.data.Url_sheet;
import com.example.office.data.User_Sheet;

import org.litepal.LitePal;

import java.util.List;
import java.util.zip.Inflater;

public class user_adapter extends RecyclerView.Adapter<user_adapter.myholder> {
    private List<User_Sheet> myitem;
    private int Ids=-1;

    public int getIds() {
        return Ids;
    }

    public void setIds(int ids) {
        this.Ids = ids;
    }

    public user_adapter(List<User_Sheet> myitem) {
        this.myitem = myitem;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_text,parent,false);
        myholder holder = new myholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final myholder holder, int position) {
        holder.passtext.setText(myitem.get(position).getPassword());
        holder.nametext.setText(myitem.get(position).getName());
        holder.date_user.setText(myitem.get(position).getDate()+"");
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
        return myitem.size();
    }

    static class myholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView nametext,passtext,date_user;
        private View view;
        public myholder(@NonNull View itemView) {
            super(itemView);
            nametext = itemView.findViewById(R.id.user_name_ada);
            passtext = itemView.findViewById(R.id.user_pass_ada);
            date_user = itemView.findViewById(R.id.user_date_ada);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(Menu.NONE,5,Menu.NONE,"删除");
            contextMenu.add(Menu.NONE,6,Menu.NONE,"删除全部");
        }
    }
    private del_interface delInterface = null;
    interface del_interface {
        void del_datainterface(int id,View view);
    }

    public void setDelInterface(del_interface delInterface) {
        this.delInterface = delInterface;
    }

    @Override
    public void onViewRecycled(@NonNull myholder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }
    public void removeItem(int post){
        int id = myitem.get(post).getId();
        LitePal.delete(User_Sheet.class,id);
        List<User_Sheet> user_sheets = LitePal.findAll(User_Sheet.class);
        this.myitem = user_sheets;
        notifyDataSetChanged();
    }

}
