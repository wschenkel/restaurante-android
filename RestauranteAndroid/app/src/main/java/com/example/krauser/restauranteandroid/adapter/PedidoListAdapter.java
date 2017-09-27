package com.example.krauser.restauranteandroid.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.model.Pedido;

import java.util.List;

public class PedidoListAdapter extends RecyclerView.Adapter<PedidoListAdapter.ViewHolder>{

    private List<Pedido> listPedido;
    private Activity activity;

    public PedidoListAdapter(List<Pedido> list, Activity activity){
        this.listPedido = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.pedido_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pedido pedido = listPedido.get(position);
        holder.txtMesa.setText(String.format("Mesa: %s", pedido.mesa));
        holder.txtData.setText(pedido.data);
        holder.txtResumo.setText(pedido.resumo);
        //Picasso.with(activity).load(item.urlImagem).into(holder.imagem);
    }

    @Override
    public int getItemCount() {
        return listPedido.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMesa;
        public TextView txtData;
        public TextView txtResumo;
        public CardView cardView;
        public ViewHolder(View itemView){
            super(itemView);
            txtMesa = (TextView)itemView.findViewById(R.id.txtMesaPedido);
            txtData = (TextView)itemView.findViewById(R.id.txtDataPedido);
            txtResumo = (TextView) itemView.findViewById(R.id.txtResumoPedido);
            cardView = (CardView)itemView.findViewById(R.id.cardViewPedidoList);
            cardView.getBackground().setAlpha(128);
        }
    }
}
