package com.anas.fragxnavxrecyclerxexcel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anas.fragxnavxrecyclerxexcel.R;
import com.anas.fragxnavxrecyclerxexcel.model.model_inner;
import com.anas.fragxnavxrecyclerxexcel.model.model_outer;

import java.util.ArrayList;

public class Adapter_Outer extends RecyclerView.Adapter<Adapter_Outer.Inner_ViewHolder> {


    public ArrayList<model_outer> arrOuter;
    ArrayList<model_inner> arrNestContainer = new ArrayList<>();

    public Adapter_Outer(ArrayList<model_outer> arrOuter) {
        this.arrOuter = arrOuter;
    }

    @NonNull
    @Override
    public Inner_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outer_row_and_recycler,parent,false);
        return new Inner_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Inner_ViewHolder holder, int position) {


        holder.txtHeading.setText(arrOuter.get(position).getHeadingName());

        holder.layLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrOuter.get(position).setExpandable(!arrOuter.get(position).isExpandable());
                arrNestContainer = arrOuter.get(position).getArrNestContainer();
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        holder.layRL.setVisibility(arrOuter.get(position).isExpandable() ? View.VISIBLE : View.GONE);

        if (arrOuter.get(position).isExpandable()){
            holder.dropDown.setImageResource(R.drawable.dropup);
        }
        else {
            holder.dropDown.setImageResource(R.drawable.dropdown);
        }



        holder.innervRV.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        Adapter_Inner_row adapter_inner_row = new Adapter_Inner_row(arrNestContainer);
        holder.innervRV.setAdapter(adapter_inner_row);

    }

    @Override
    public int getItemCount() {
        return arrOuter.size();
    }

    public class Inner_ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView innervRV;
        RelativeLayout layRL;
        LinearLayout layLL;
        ImageView dropDown;
        TextView txtHeading;
        public Inner_ViewHolder(@NonNull View itemView) {
            super(itemView);

            innervRV=itemView.findViewById(R.id.innervRV);
            layRL=itemView.findViewById(R.id.layRL);
            layLL=itemView.findViewById(R.id.layLL);
            dropDown=itemView.findViewById(R.id.dropDown);
            txtHeading=itemView.findViewById(R.id.txtHeading);
        }
    }
}
