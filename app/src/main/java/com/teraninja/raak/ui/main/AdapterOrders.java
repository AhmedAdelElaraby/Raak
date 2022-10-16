package com.teraninja.raak.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teraninja.raak.DataClinet.OnClickRequste;
import com.teraninja.raak.Model.DataOrdersServes;
import com.teraninja.raak.Model.dataRecHome;
import com.teraninja.raak.R;
import com.teraninja.raak.databinding.ItemRecHomeBinding;
import com.teraninja.raak.databinding.ItemorderBinding;

import java.util.ArrayList;


public class AdapterOrders extends RecyclerView.Adapter<AdapterOrders.ViewHolder>{
    ArrayList<DataOrdersServes> list = new ArrayList<>();
    OnClickRequste onClickRequste;

    public AdapterOrders(OnClickRequste onClickRequste) {
        this.onClickRequste = onClickRequste;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemorderBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.itemorder,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.texttime.setText(""+list.get(position).getCreated_at());
        holder.recyclerBinding.texttwo.setText(holder.itemView.getContext().getString(R.string.unit_number)+list.get(position).getBuilding().getResidential_unit_number());
        holder.recyclerBinding.textthree.setText(holder.itemView.getContext().getString(R.string.Holiday_type)+" : "+list.get(position).getDamage_type().getName());
        holder.recyclerBinding.caseitem.setText(list.get(position).getStatus());
       int servesid= list.get(position).getId();
        holder.recyclerBinding.caseitemtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequste.getItem(list.get(position).getCreated_at(),""+list.get(position).getBuilding().getResidential_unit_number(),
                        ""+list.get(position).getStatus(),servesid);
            }
        });
        String caseif=list.get(position).getStatus();
        if (caseif.equals("finished")){
            holder.recyclerBinding.caseitemtwo.setEnabled(true);
            holder.recyclerBinding.caseitemtwo.setBackground(holder.itemView.getResources().getDrawable(R.drawable.itemtextorderunder));

        }else{
            holder.recyclerBinding.caseitemtwo.setBackground(holder.itemView.getResources().getDrawable(R.drawable.itemnotenbele));
            holder.recyclerBinding.caseitemtwo.setEnabled(false);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<DataOrdersServes> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemorderBinding recyclerBinding;


        public ViewHolder( ItemorderBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
