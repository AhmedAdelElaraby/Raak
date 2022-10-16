package com.teraninja.raak.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;
import com.teraninja.raak.DataClinet.OnClickItemHome;
import com.teraninja.raak.Model.DataHomepost;
import com.teraninja.raak.Model.dataRecHome;
import com.teraninja.raak.R;
import com.teraninja.raak.databinding.ItemRecHomeBinding;

import java.util.ArrayList;


public class AdapterHomePager extends RecyclerView.Adapter<AdapterHomePager.ViewHolder>{
    ArrayList<DataHomepost> list = new ArrayList<>();
    OnClickItemHome home;

    public AdapterHomePager(OnClickItemHome home) {
        this.home = home;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemRecHomeBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_rec_home,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.name.setText(list.get(position).getArea().getName());

        holder.recyclerBinding.textseelcte.setText(""+list.get(position).getSize());
        holder.recyclerBinding.bathroom.setText(""+list.get(position).getBath_rooms());
        holder.recyclerBinding.bedroom.setText(""+list.get(position).getBed_rooms());
        Picasso.get().load(list.get(position).getImage()).fit().into(holder.recyclerBinding.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.getBuildingId(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<DataHomepost> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemRecHomeBinding recyclerBinding;


        public ViewHolder( ItemRecHomeBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
