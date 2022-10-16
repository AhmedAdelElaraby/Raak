package com.teraninja.raak.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.raak.Model.Areaa;
import com.teraninja.raak.Model.DataHomepost;
import com.teraninja.raak.R;
import com.teraninja.raak.databinding.ItemAreaBinding;

import java.util.ArrayList;


public class AdapterArea extends RecyclerView.Adapter<AdapterArea.ViewHolder>{
    ArrayList<Areaa> list = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemAreaBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_area,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.textarea.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<Areaa> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemAreaBinding recyclerBinding;


        public ViewHolder( ItemAreaBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
