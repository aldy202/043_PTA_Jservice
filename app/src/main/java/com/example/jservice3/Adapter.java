package com.example.jservice3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<User> userList;
    Context context;

    public Adapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {

        holder.text_nama.setText(userList.get(position).getNamapemilik());
        holder.text_jenis.setText(userList.get(position).getJenisbarang());
        holder.text_detail.setText(userList.get(position).getDetail());
        holder.text_metode.setText(userList.get(position).getMetode());
        holder.text_status.setText(userList.get(position).getStatus());


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView text_nama,text_jenis,text_detail,text_metode,text_status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_nama = itemView.findViewById(R.id.text_nama);
            text_jenis = itemView.findViewById(R.id.text_jenis);
            text_detail = itemView.findViewById(R.id.text_detail);
            text_metode = itemView.findViewById(R.id.text_metode);
            text_status = itemView.findViewById(R.id.text_status);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, EditorActivity.class);
            intent.putExtra("id",userList.get(getAdapterPosition()).getId());
            intent.putExtra("namapemilik",userList.get(getAdapterPosition()).getNamapemilik());
            intent.putExtra("jenisbarang",userList.get(getAdapterPosition()).getJenisbarang());
            intent.putExtra("detail",userList.get(getAdapterPosition()).getDetail());
            intent.putExtra("metode",userList.get(getAdapterPosition()).getMetode());
            intent.putExtra("status",userList.get(getAdapterPosition()).getStatus());
            context.startActivity(intent);

        }
    }
}
