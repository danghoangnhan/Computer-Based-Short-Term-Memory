package com.example.memorygame.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.R;
import com.example.memorygame.utilities.ItemTouchHelperViewHolder;

public class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    public final TextView customerName, customerEmail;
    public final ImageView handleView, profileImage;


    public ItemViewHolder(View itemView) {
        super(itemView);
        customerName = (TextView)itemView.findViewById(R.id.text_view_customer_name);
        customerEmail = (TextView)itemView.findViewById(R.id.text_view_customer_email);
        handleView = (ImageView)itemView.findViewById(R.id.handle);
        profileImage = (ImageView)itemView.findViewById(R.id.image_view_customer_head_shot);
    }

    @Override
    public void onItemSelected() {
//        itemView.setBahttp://valokafor.com/wp-admin/post.php?post=1804&action=edit#ckgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
        itemView.setBackgroundColor(0);
    }
}