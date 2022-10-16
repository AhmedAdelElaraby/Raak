package com.teraninja.raak.ui.main;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.raak.DataClinet.OnClickItemHome;
import com.teraninja.raak.DataClinet.OnClick_Id;
import com.teraninja.raak.Model.DamageType;
import com.teraninja.raak.Model.DataHomepost;
import com.teraninja.raak.R;
import com.teraninja.raak.databinding.ItemRecHomeBinding;
import com.teraninja.raak.databinding.ItemrecfinshBinding;

import java.util.ArrayList;


public class AdapterDamage extends RecyclerView.Adapter<AdapterDamage.ViewHolder>{
    ArrayList<DamageType> list = new ArrayList<>();
OnClick_Id onClick_id;

    int selectPosition= 0;

    public AdapterDamage(OnClick_Id onClick_id) {
        this.onClick_id = onClick_id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemrecfinshBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.itemrecfinsh,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.text.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getLogo()).into(holder.recyclerBinding.image);


    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onClick_id.getId(list.get(position).getId());
            int pos=holder.getAdapterPosition();
            selectPosition=pos;
            notifyDataSetChanged();

        }
    });
        if (selectPosition==position){

            holder.recyclerBinding.l1.setBackground(holder.itemView.getResources().getDrawable(R.drawable.item_backgruond_maintenance));

        }else {
            holder.recyclerBinding.l1.setBackground(null);

        }
    }







    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<DamageType> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemrecfinshBinding recyclerBinding;


        public ViewHolder( ItemrecfinshBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
