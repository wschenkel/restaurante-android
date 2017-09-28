package com.example.krauser.restauranteandroid.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.model.Item;
import com.example.krauser.restauranteandroid.model.ItemPedido;

import java.util.List;

public class itemPedidoListAdapter extends RecyclerView.Adapter<itemPedidoListAdapter.ViewHolder>{

    private List<Item> listItem;
    private Activity activity;

    public itemPedidoListAdapter(List<Item> list, Activity activity){
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
        holder.tituloItemList.setText(item.titulo);
        holder.imgItemList.setImageURI(Uri.parse(item.urlImagem.toString()));
        //Picasso.with(activity).load(item.urlImagem).into(holder.imagem);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloItemList;
        public ImageView imgItemList;
        public CardView cardViewItemList;
        public ViewHolder(View itemView){
            super(itemView);
            tituloItemList = (TextView)itemView.findViewById(R.id.tituloItemList);
            imgItemList = (ImageView)itemView.findViewById(R.id.imgItemList);
            cardViewItemList = (CardView)itemView.findViewById(R.id.cardViewItemList);
            //cardView.getBackground().se;
        }
    }
}
