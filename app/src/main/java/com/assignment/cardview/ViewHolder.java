package com.assignment.cardview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public  class ViewHolder extends RecyclerView.ViewHolder {

    View mView;


    public ViewHolder(@NonNull View itemView) {
        super( itemView );

        mView = itemView;

        itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mClickListener.onItemClick( view,getAdapterPosition() );

            }
        } );

        itemView.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                mClickListener.onItemLongClick( view,getAdapterPosition() );
                return true;
            }
        } );
    }

    public void setDetails(Context ctx,String title,String description,String image){
        //Views
        TextView mTitleTv = mView.findViewById(R.id.rTitleTv);
        TextView mDetailTv = mView.findViewById(R.id.rDescriptionTv);
        ImageView mImageIv = mView.findViewById(R.id.rImageView);
        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(description);
        Picasso.get().load(image).into(mImageIv);
    }

    private ViewHolder.Clicklistener mClickListener;

    public interface Clicklistener {

        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnClickListener(ViewHolder.Clicklistener clickListener)
    {
        mClickListener = clickListener;
    }
}
