package com.progtools.eventmanagement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    String []items;
    Context context;
    public Adapter(Context context, String[]items){

        this.context=context;
        this.items=items;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.custom_row, parent, false);
        Item item= new Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {


        ((Item)holder).textView.setText(items[position]);
        ((Item)holder).button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked at position " + position, Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, event_detail.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
    public class Item extends RecyclerView.ViewHolder {

        TextView textView;
        Button button;

        public Item( View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.item);
            button=itemView.findViewById(R.id.button);

        }
    }
}
