package com.ayra.petshelter.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ayra.petshelter.R;
import com.squareup.picasso.Picasso;

class ViewHolder extends RecyclerView.ViewHolder {

    private View mView;
    private ViewHolder.ClickListener mClickListener;

    ViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

        //item click
        itemView.setOnClickListener(view -> mClickListener.onItemClick(view, getAdapterPosition()));

        itemView.setOnLongClickListener(view -> {
            mClickListener.onItemLongClick(view, getAdapterPosition());
            return true;
        });

    }

    void setDetails(Context context, String name, String breed, String url, String desc) {
        //Views
        TextView mTitleTv = mView.findViewById(R.id.rTitleTv);
        TextView mDetailTv = mView.findViewById(R.id.rDescriptionTv);
        ImageView mImageIv = mView.findViewById(R.id.rImageView);
        TextView mDetail = mView.findViewById(R.id.rDescription);
        //set data to views
        mTitleTv.setText(name);
        mDetailTv.setText(breed);
        mDetail.setText(desc);
        Picasso.get().load(url).into(mImageIv);
    }

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

}
