package com.teraninja.raak.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.raak.Model.Areaa;
import com.teraninja.raak.Model.NotificationDetilse;
import com.teraninja.raak.Model.Notifications;
import com.teraninja.raak.R;
import com.teraninja.raak.databinding.ItemAreaBinding;
import com.teraninja.raak.databinding.ItemNotificationsBinding;

import java.util.ArrayList;


public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.ViewHolder>{
    ArrayList<NotificationDetilse> list = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemNotificationsBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_notifications,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.texttitile.setText(list.get(position).getTitle());
        holder.recyclerBinding.textDescrbtion.setText(list.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<NotificationDetilse> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemNotificationsBinding recyclerBinding;


        public ViewHolder( ItemNotificationsBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
