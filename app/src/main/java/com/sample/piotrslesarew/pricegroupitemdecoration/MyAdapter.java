package com.sample.piotrslesarew.pricegroupitemdecoration;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements StickyHeaderAdapter<MyAdapter.HeaderHolder> {

    private Color[] data;

    MyAdapter(Color[] data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.color_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int colorValue = data[position].getColorValue();
        int backgroundColor = ContextCompat.getColor(holder.itemView.getContext(), colorValue);
        holder.itemView.setBackgroundColor(backgroundColor);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    @Override
    public long getHeaderId(int position) {
        return (long) data[position].getPrice();
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return new HeaderHolder(((TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.color_header_layout, parent, false)));
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        ((TextView) viewholder.itemView).setText(String.valueOf(data[position].getPrice()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {
        HeaderHolder(TextView itemView) {
            super(itemView);
        }
    }
}
