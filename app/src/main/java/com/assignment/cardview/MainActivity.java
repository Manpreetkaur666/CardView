package com.assignment.cardview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    LinearLayoutManager mLayoutManager; //for sorting
    SharedPreferences mSharedPref; //for saving sort settings
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Model>options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        mLayoutManager = new LinearLayoutManager( this );
        mLayoutManager.setReverseLayout( true );
        mLayoutManager.setStackFromEnd( true );

        mRecyclerView = findViewById( R.id.recyclerView );
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Data");

        showData();

    }

 private void showData()
 {
     options = new FirebaseRecyclerOptions.Builder<Model>().setQuery( mRef,Model.class ).build();

     firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
         @Override
         protected void onBindViewHolder(@NonNull ViewHolder holder,int position,@NonNull Model model) {

             holder.setDetails( getApplicationContext(),model.getTitle(),model.getDescription(),model.getImage() );

         }

         @NonNull
         @Override
         public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {

             View itemView = LayoutInflater.from( parent.getContext()).inflate( R.layout.row1,parent,false ) ;

             ViewHolder viewHolder = new ViewHolder( itemView );
             viewHolder.setOnClickListener( new ViewHolder.Clicklistener() {
                 @Override
                 public void onItemClick(View view,int position) {

                     Toast.makeText( MainActivity.this,"Hello",Toast.LENGTH_SHORT ).show();

                 }

                 @Override
                 public void onItemLongClick(View view,int position) {

                     Toast.makeText( MainActivity.this,"Long Clicked",Toast.LENGTH_SHORT ).show();

                 }
             } );
             return viewHolder;
         }
     };

     mRecyclerView.setLayoutManager( mLayoutManager );
     firebaseRecyclerAdapter.startListening();
     mRecyclerView.setAdapter( firebaseRecyclerAdapter );
 }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseRecyclerAdapter!=null)
        {

            firebaseRecyclerAdapter.startListening();
        }
    }
}