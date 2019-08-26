package com.ayra.petshelter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class FragmentAdopt extends Fragment {

    private Activity mActivity;
    private RecyclerView mRecyclerView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;
    private FirebaseRecyclerAdapter<Model, ViewHolder> mAdapter;
    private TextView tvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_adopt, container, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Posts");

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();

        LinearLayoutManager mManager = new LinearLayoutManager(mActivity);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mManager);

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(mRef, Model.class)
                        .build();

        mAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(ViewHolder viewHolder, int position, Model model) {
                viewHolder.setDetails(getContext(), model.getName(), model.getBreed(), model.getUrl(), model.getDesc());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                        TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                        TextView mDesc2 = view.findViewById(R.id.rDescription);
                        ImageView mImageView = view.findViewById(R.id.rImageView);
                        //get data from views
                        String mTitle = mTitleTv.getText().toString();
                        String mDesc = mDescTv.getText().toString();
                        String mNewDesc = mDesc2.getText().toString();
                        Drawable mDrawable = mImageView.getDrawable();
                        Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

                        //pass this data to new activity
                        Intent intent = new Intent(view.getContext(), Detail.class);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] bytes = stream.toByteArray();
                        intent.putExtra("url", bytes); //put bitmap image as array of bytes
                        intent.putExtra("name", mTitle); // put title
                        intent.putExtra("breed", mDesc);
                        intent.putExtra("desc", mNewDesc);//put description
                        startActivity(intent); //start activity
                    }
                });
            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row, parent, false);

                return new ViewHolder(view);
            }
        };

        mAdapter.startListening();
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();







    }

}
