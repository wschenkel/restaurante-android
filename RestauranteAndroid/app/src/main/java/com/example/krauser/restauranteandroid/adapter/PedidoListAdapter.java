package com.example.krauser.restauranteandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.activity.NovoPedido;
import com.example.krauser.restauranteandroid.model.Pedido;

import java.util.List;

public class PedidoListAdapter extends RecyclerView.Adapter<PedidoListAdapter.ViewHolder>{

    private List<Pedido> listPedido;
    private final Activity activity;

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
        final Pedido pedido = listPedido.get(position);
        holder.txtMesa.setText(String.format("Mesa: %s", pedido.mesa));
        holder.txtData.setText(pedido.data);
        holder.txtResumo.setText(pedido.resumo);
        final Context context = activity;
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pedidoActivity = new Intent(activity, NovoPedido.class);
                pedidoActivity.putExtra("pedido", pedido);
                activity.startActivity(pedidoActivity);
            }
        });
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
            txtMesa = itemView.findViewById(R.id.txtMesaPedido);
            txtData = itemView.findViewById(R.id.txtDataPedido);
            txtResumo = itemView.findViewById(R.id.txtResumoPedido);
            cardView = itemView.findViewById(R.id.cardViewPedidoList);
            cardView.getBackground().setAlpha(128);
        }
    }
}
