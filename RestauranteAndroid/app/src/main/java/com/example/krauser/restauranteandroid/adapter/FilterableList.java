package com.example.krauser.restauranteandroid.adapter;

import android.content.ClipData;
import android.support.annotation.NonNull;

import com.example.krauser.restauranteandroid.model.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

public class FilterableList<T> implements List {

    private List<Item> itens;
    private List<Item> allItens;

    private String filter;

    public FilterableList(){
        this.allItens = itens = new ArrayList<>();
    }

    public FilterableList(List<Item> itens){
        this.allItens = this.itens = itens;
    }

    public void setFilter(String filter){
        this.filter = filter;
        refresh();
    }

    public void clearFilter(){
        this.filter = null;
        refresh();
    }

    public List<Item> getAllItens() {
        return allItens;
    }

    private void refresh(){
        if(filter == null || filter.isEmpty() || filter.toLowerCase().equals("todos")){
            itens = allItens;
            return;
        }
        itens = new ArrayList<>();
        for(Item i : allItens)
            if(filter.toLowerCase().equals(i.categoria.toLowerCase()))
                itens.add(i);
    }

    @Override
    public int size() {
        return itens.size();
    }

    @Override
    public boolean isEmpty() {
        return itens.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return itens.contains((T)o);
    }

    @NonNull
    @Override
    public Iterator iterator() {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return itens.toArray();
    }

    @Override
    public boolean add(Object o) {
        boolean added = allItens.add((Item)o);
        refresh();
        return added;
    }

    @Override
    public boolean remove(Object o) {
        boolean removed = allItens.remove((T)o);
        refresh();
        return removed;
    }

    @Override
    public boolean addAll(@NonNull Collection collection) {
        return itens.addAll(collection);
    }

    @Override
    public boolean addAll(int i, @NonNull Collection collection) {
        return itens.addAll(i, collection);
    }

    @Override
    public void clear() {
        itens.clear();
    }

    @Override
    public boolean equals(Object o) {
        return itens.equals(o);
    }

    @Override
    public int hashCode() {
        return itens.hashCode();
    }

    @Override
    public Object get(int i) {
        return itens.get(i);
    }

    @Override
    public Object set(int i, Object o) {
        return itens.set(i, (Item)o);
    }

    @Override
    public void add(int i, Object o) {
        itens.add(i, (Item)o);
    }

    @Override
    public Object remove(int i) {
        return itens.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return itens.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return itens.lastIndexOf(o);
    }

    @Override
    public ListIterator listIterator() {
        return itens.listIterator();
    }

    @NonNull
    @Override
    public ListIterator listIterator(int i) {
        return itens.listIterator(i);
    }

    @NonNull
    @Override
    public List subList(int i, int i1) {
        return itens.subList(i, i1);
    }

    @Override
    public boolean retainAll(@NonNull Collection collection) {
        return itens.retainAll(collection);
    }

    @Override
    public boolean removeAll(@NonNull Collection collection) {
        return itens.removeAll(collection);
    }

    @Override
    public boolean containsAll(@NonNull Collection collection) {
        return itens.containsAll(collection);
    }

    @NonNull
    @Override
    public Object[] toArray(@NonNull Object[] objects) {

        return itens.toArray(objects);
    }


}
