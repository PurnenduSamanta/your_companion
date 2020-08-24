package com.example.testing;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> {


    Context c;
    ArrayList<Model>models;
    public  RecylerViewAdapter(Context c) {
        this.c=c;
        DbHelper db=new DbHelper(c);
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
     holder.title.setText(models.get(position).getTitle());
     holder.delete.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             DbHelper db=new DbHelper(c);
             int b=db.deleteData(models.get(position).getId());
             if(b!=-1)
             {
                 Toast.makeText(c, "Operation Successful", Toast.LENGTH_SHORT).show();
                 ArrayList<Model>newDataCollectedFromDatabase=db.getdata();
                 RecylerViewAdapter.this.loadWithNewData(newDataCollectedFromDatabase);
             }
             else
             {
                 Toast.makeText(c, "Something Wrong happend", Toast.LENGTH_SHORT).show();
             }

         }
     });
     holder.parent.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(c,Details.class);
             String str=models.get(position).getDesc();
             String str1=models.get(position).getTitle();
             int a=models.get(position).getId();
             intent.putExtra("message_key", str);
             intent.putExtra("message_keya",str1);
             intent.putExtra("message_keyb",a);
             c.startActivity(intent);

         }
     });
    }

    public void setModels(ArrayList<Model> models) {
        this.models = models;
        notifyDataSetChanged();
    }
    public void loadWithNewData(ArrayList<Model> newCollectedData) {
        DbHelper db=new DbHelper(c);
        boolean a=db.chkDatabaseEmpty();
        if(a) {
            this.models.clear();
            this.models = newCollectedData;
            this.notifyDataSetChanged();
        }
        else
        {
            Intent intent=new Intent(c,empty_activity.class);
            c.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public  class ViewHolder extends  RecyclerView.ViewHolder {
        CardView parent;
        ImageButton delete;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            delete=itemView.findViewById(R.id.delete);
            title=itemView.findViewById(R.id.title);
        }
    }
}
