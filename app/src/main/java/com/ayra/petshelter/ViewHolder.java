package com.ayra.petshelter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    public ViewHolder.ClickListener mClickListener;


    public ViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemlongClick(view, getAdapterPosition());
                return true;
            }
        });

    }

    public void setDetails(Context ctx, String name, String breed, String url, String desc){
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
        void onItemlongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

}
