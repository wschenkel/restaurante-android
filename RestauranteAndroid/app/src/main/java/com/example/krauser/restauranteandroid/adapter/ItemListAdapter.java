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
    int selected_position = 0;

    public ItemListAdapter(List<Item> list, Activity activity){
        this.listItem = list;
        this.activity = activity;
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
        ArrayList<String> i = new ArrayList<String>();


        holder.tituloItem.setText(item.titulo);
        holder.valorItem.setText(String.format("R$ %s", item.valor));
        if(item.resource > 0) {
            holder.imgItem.setImageResource(item.resource);
        }

        if (selected_position == position) {
            if (holder.itemView.getBackground().equals(Color.GRAY)) {
                holder.itemView.setBackgroundColor(Color.WHITE);
                i.remove(holder.cardViewItem.findViewById(R.id.cardViewItem).toString());
            } else {
                holder.itemView.setBackgroundColor(Color.GRAY);
                i.add(holder.cardViewItem.findViewById(R.id.cardViewItem).toString());
            }
        }

        /*
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent pedidoActivity = new Intent(activity, NovoPedido.class);
            pedidoActivity.putExtra("pedido", pedido);
            activity.startActivity(pedidoActivity);
            }
        });*/
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
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);
        }
    }
}