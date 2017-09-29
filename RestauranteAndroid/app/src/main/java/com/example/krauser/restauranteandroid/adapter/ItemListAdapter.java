package com.example.krauser.restauranteandroid.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder>{

    private List<Item> listItem;
    private Activity activity;
    private SparseBooleanArray selectedItems;

    public ItemListAdapter(List<Item> list, Activity activity){
        this.listItem = list;
        this.activity = activity;
    }

    public void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<Integer>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_pedido_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = listItem.get(position);
        holder.tituloItem.setText(item.titulo);
        holder.valorItem.setText(String.format("R$ %s", item.valor));
        if(item.resource > 0)
            holder.imgItem.setImageResource(item.resource);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloItem;
        public TextView valorItem;
        public ImageView imgItem;
        public CardView cardViewItem;

        public ViewHolder(View itemView){
            super(itemView);
            tituloItem = itemView.findViewById(R.id.tituloItem);
            valorItem = itemView.findViewById(R.id.valorItem);
            imgItem = itemView.findViewById(R.id.imgItem);
            cardViewItem = itemView.findViewById(R.id.cardViewItem);
            cardViewItem.getBackground().setAlpha(128);
        }
    }
}
