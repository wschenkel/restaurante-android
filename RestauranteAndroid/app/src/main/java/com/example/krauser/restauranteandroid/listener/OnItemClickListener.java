package com.example.krauser.restauranteandroid.listener;

import com.example.krauser.restauranteandroid.adapter.ItemListAdapter;

public interface OnItemClickListener {
    void notifyClick(ItemListAdapter.ViewHolder holder);
}
