package com.example.memorygame.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Object.Customer;
import com.example.memorygame.R;
import com.example.memorygame.ViewHolder.ItemViewHolder;
import com.example.memorygame.listener.OnCustomerListChangedListener;
import com.example.memorygame.listener.OnStartDragListener;
import com.example.memorygame.utilities.ItemTouchHelperAdapter;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by Valentine on 10/18/2015.
 */
public class CustomerListAdapter extends RecyclerView.Adapter<ItemViewHolder> implements ItemTouchHelperAdapter {

    private List<Customer> mCustomers;
    private Context mContext;
    private OnStartDragListener mDragStartListener;
    private OnCustomerListChangedListener mListChangedListener;

    public CustomerListAdapter(List<Customer> customers, Context context, OnStartDragListener dragLlistener, OnCustomerListChangedListener listChangedListener){
        mCustomers = customers;
        mContext = context;
        mDragStartListener = dragLlistener;
        mListChangedListener = listChangedListener;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.row_customer_list, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {

        final Customer selectedCustomer = mCustomers.get(position);

        holder.customerName.setText(selectedCustomer.getName());
        holder.customerEmail.setText(selectedCustomer.getEmailAddress());
        Picasso.with(mContext)
                .load(selectedCustomer.getImagePath())
                .placeholder(R.drawable.code)
                .into(holder.profileImage);

        holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCustomers.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mCustomers, fromPosition, toPosition);
        mListChangedListener.onNoteListChanged(mCustomers);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {

    }
}