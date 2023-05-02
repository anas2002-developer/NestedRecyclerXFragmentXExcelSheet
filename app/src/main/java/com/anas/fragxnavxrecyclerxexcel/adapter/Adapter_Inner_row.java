package com.anas.fragxnavxrecyclerxexcel.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.anas.fragxnavxrecyclerxexcel.Fragments.StudentFragment;
import com.anas.fragxnavxrecyclerxexcel.R;
import com.anas.fragxnavxrecyclerxexcel.StudListActivity;
import com.anas.fragxnavxrecyclerxexcel.model.model_inner;

import java.util.ArrayList;

public class Adapter_Inner_row extends RecyclerView.Adapter<Adapter_Inner_row.Outer_ViewHolder> {

    ArrayList<model_inner> arrInner;


    public Adapter_Inner_row(ArrayList<model_inner> arrInner) {
        this.arrInner = arrInner;
    }

    @NonNull
    @Override
    public Outer_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_row,parent,false);
        return new Outer_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Outer_ViewHolder holder, int position) {

        holder.txtItem_name.setText(arrInner.get(position).getName());

        holder.layLL_inner.setOnClickListener(v -> {
            AppCompatActivity activity=(AppCompatActivity) v.getContext();
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layFL, new StudentFragment(arrInner.get(position).getName(), arrInner.get(position).getFname()))
                    .addToBackStack(null)
                            .commit();
//            Intent i = new Intent(holder.itemView.getContext(), StudListActivity.class);
//            i.putExtra("Subject",arrInner.get(position).getName());
//            holder.itemView.getContext().startActivity(i);

        });

    }

    @Override
    public int getItemCount() {
        return arrInner.size();
    }

    public class Outer_ViewHolder extends RecyclerView.ViewHolder {

        TextView txtItem_name;
        LinearLayout layLL_inner;
        public Outer_ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtItem_name=itemView.findViewById(R.id.txtItem_name);
            layLL_inner=itemView.findViewById(R.id.layLL_inner);

        }
    }
}
