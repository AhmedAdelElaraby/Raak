package com.teraninja.raak.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.raak.DataClinet.OnClickgetitemRepire;
import com.teraninja.raak.Model.DataRepire;
import com.teraninja.raak.Model.MaintenanceContract;
import com.teraninja.raak.R;
import com.teraninja.raak.databinding.ItemMaintenceBinding;

import java.util.ArrayList;


public class AdapterMaintencene extends RecyclerView.Adapter<AdapterMaintencene.ViewHolder>{
    ArrayList<DataRepire> list = new ArrayList<>();
    OnClickgetitemRepire repire;
    ArrayList<ArrayList<MaintenanceContract>> list1= new ArrayList();

    public AdapterMaintencene(OnClickgetitemRepire repire) {
        this.repire = repire;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemMaintenceBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_maintence,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.number.setText(""+list.get(position).getResidential_unit_number());
        holder.recyclerBinding.location.setText(""+list.get(position).getAddress());
       // Toast.makeText(holder.itemView.getContext(), ""+list.get(position).getMaintenance_contract().size(), Toast.LENGTH_SHORT).show();
        list1.add(list.get(position).getMaintenance_contract());
        //Toast.makeText(holder.itemView.getContext(), ""+list1.get(position). Toast.LENGTH_SHORT).show();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              repire.getData(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<DataRepire> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemMaintenceBinding recyclerBinding;


        public ViewHolder( ItemMaintenceBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
