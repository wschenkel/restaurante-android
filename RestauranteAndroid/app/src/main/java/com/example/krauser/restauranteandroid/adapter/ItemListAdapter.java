package com.example.krauser.restauranteandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> implements OnItemClickListener{

    private FilterableList<Item> itens;
    private Activity activity;
    private boolean selectable;
    private List<String> selectedIds;
    private int itensCarregados;
    private boolean isLoaded = false;

    public ItemListAdapter(FilterableList<Item> list, Activity activity){
        this.itens = list;
        this.activity = activity;
        this.selectedIds = new ArrayList<>();
    }

    public void setSelectable(boolean selectable){
        this.selectable = selectable;
    }

    public void setFilter(String filter){
        itens.setFilter(filter);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_pedido_list_item, parent, false);
        return new ViewHolder(view);
    }

    public List<Item> getSelectedItens(){
        List<Item> listSelecionados = new ArrayList<Item>();

        for (Item item : itens.getAllItens()) {
            if (selectedIds.contains(String.valueOf(item.id))) {
                listSelecionados.add(item);
            }
         }

        return listSelecionados;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = (Item)itens.get(position);
        holder.listener = this;
        holder.tituloItem.setText(item.titulo);
        holder.valorItem.setText(String.format("R$ %.2f", item.valor));
        holder.item = item;

        if(item.resource > 0) {
            holder.imgItem.setImageResource(item.resource);
        }
        holder.context = activity;

        if(selectedIds.contains(String.valueOf(item.id))){
            holder.itemView.setBackgroundColor(Color.GRAY);
            holder.itemView.getBackground().setAlpha(128);
        }else{
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.itemView.getBackground().setAlpha(128);
        }
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    @Override
    public void notifyClick(ViewHolder holder) {
        Item item = holder.item;
        if(selectedIds.contains(String.valueOf(item.id))){
            selectedIds.remove(String.valueOf(item.id));
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.itemView.getBackground().setAlpha(128);
        }else{
            selectedIds.add(String.valueOf(item.id));
            holder.itemView.setBackgroundColor(Color.GRAY);
            holder.itemView.getBackground().setAlpha(128);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tituloItem;
        public TextView valorItem;
        public ImageView imgItem;
        public CardView cardViewItem;
        public Context context;
        public OnItemClickListener listener;
        public Item item;

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
                listener.notifyClick(this);
        }
    }
}