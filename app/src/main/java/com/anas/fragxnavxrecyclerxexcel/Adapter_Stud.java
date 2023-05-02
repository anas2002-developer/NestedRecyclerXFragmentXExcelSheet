package com.anas.fragxnavxrecyclerxexcel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_Stud extends RecyclerView.Adapter<Adapter_Stud.Stud_ViewHolder> {

    Context context;
    ArrayList<Model_Stud> arrStud = new ArrayList<>();

    public ArrayList<Model_Stud> arrStud_present = new ArrayList<>();
    public ArrayList<Model_Stud> arrStud_absent = new ArrayList<>();


    public Adapter_Stud(Context context, ArrayList<Model_Stud> arrStud) {
        this.context = context;
        this.arrStud = arrStud;
    }

    @NonNull
    @Override
    public Stud_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_row,parent,false);
        return new Stud_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Stud_ViewHolder holder, int position) {
        holder.imgStud_img.setImageResource(arrStud.get(position).getStud_img());
        holder.txtStud_name.setText(arrStud.get(position).getStud_name());
        holder.txtStud_uid.setText(arrStud.get(position).getStud_uid());

        arrStud_absent.add(arrStud.get(position));
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                CheckBox checkBox = (CheckBox) v;

                if (checkBox.isChecked()){
                    arrStud_present.add(arrStud.get(pos));
                    arrStud_absent.remove(arrStud.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrStud.size();
    }

    public class Stud_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        ImageView imgStud_img;
        TextView txtStud_name,txtStud_uid;
        CheckBox checkBox;

        ItemClickListener itemClickListener;
        public Stud_ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgStud_img = itemView.findViewById(R.id.imgStud_img);
            txtStud_name = itemView.findViewById(R.id.txtStud_name);
            txtStud_uid = itemView.findViewById(R.id.txtStud_uid);
            checkBox = itemView.findViewById(R.id.checkBox);

            checkBox.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener=ic;
        }
        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
    }
}
