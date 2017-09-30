package com.example.krauser.restauranteandroid.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.activity.NovoPedido;
import com.example.krauser.restauranteandroid.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    private List<Item> listItem;
    private Activity activity;
    private boolean selectable;
    private List<Item> selectedItens;
    private int itensCarregados;


    public ItemListAdapter(List<Item> list, Activity activity){
        this.listItem = list;
        this.activity = activity;
        this.selectedItens = new ArrayList<>();
    }

    public void setSelectable(boolean selectable){
        this.selectable = selectable;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_pedido_list_item, parent, false);
        return new ViewHolder(view);
    }

    public List<Item> getSelectedItens(){
        return selectedItens;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = listItem.get(position);
        holder.tituloItem.setText(item.titulo);
        holder.valorItem.setText(String.format("R$ %s", item.valor));
        if(item.resource > 0) {
            holder.imgItem.setImageResource(item.resource);
        }

        if(isLoaded()){
            if(selectable){
                if(selectedItens.contains(item)){
                    selectedItens.remove(item);
                    holder.itemView.setBackgroundColor(Color.GRAY);
                }else{
                    selectedItens.add(item);
                    holder.itemView.setBackgroundColor(Color.WHITE);
                }
            }
        }else{
            itensCarregados++;
        }
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tituloItem;
        public TextView valorItem;
        public ImageView imgItem;
        public CardView cardViewItem;

        public ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            tituloItem = itemView.findViewById(R.id.tituloItem);
            valorItem = itemView.findViewById(R.id.valorItem);
            imgItem = itemView.findViewById(R.id.imgItem);
            cardViewItem = itemView.findViewById(R.id.cardViewItem);
            cardViewItem.getBackground().setAlpha(128);
        }

        @Override
        public void onClick(View v) {
            if(selectable)
                notifyItemChanged(getAdapterPosition());
        }
    }

    private boolean isLoaded(){
        return itensCarregados == listItem.size();
    }
}