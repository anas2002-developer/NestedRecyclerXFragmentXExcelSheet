package com.anas.fragxnavxrecyclerxexcel.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anas.fragxnavxrecyclerxexcel.adapter.Adapter_Outer;
import com.anas.fragxnavxrecyclerxexcel.R;
import com.anas.fragxnavxrecyclerxexcel.model.model_inner;
import com.anas.fragxnavxrecyclerxexcel.model.model_outer;

import org.apache.poi.hssf.usermodel.HSSFRow;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    String Fname;
    RecyclerView vRV;
    ArrayList<model_outer> arrOuter = new ArrayList<>();
    Adapter_Outer adapter_outer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        vRV=view.findViewById(R.id.vRV);
        vRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<model_inner> arrNest1 = new ArrayList<>();
        ArrayList<model_inner> arrNest2 = new ArrayList<>();
        ArrayList<model_inner> arrNest3 = new ArrayList<>();
        ArrayList<model_inner> arrNest4 = new ArrayList<>();
        ArrayList<model_inner> arrNest5 = new ArrayList<>();
        ArrayList<model_inner> arrNest6 = new ArrayList<>();






        arrNest1.add(new model_inner("Maths",Fname));
        arrNest1.add(new model_inner("Science",Fname));
        arrNest1.add(new model_inner("English",Fname));
        arrNest1.add(new model_inner("Computer",Fname));
        arrNest1.add(new model_inner("Sports",Fname));

        arrNest2.add(new model_inner("Maths",Fname));
        arrNest2.add(new model_inner("Science",Fname));
        arrNest2.add(new model_inner("English",Fname));
        arrNest2.add(new model_inner("Computer",Fname));
        arrNest2.add(new model_inner("Sports",Fname));

        arrNest3.add(new model_inner("Maths",Fname));
        arrNest3.add(new model_inner("Science",Fname));
        arrNest3.add(new model_inner("English",Fname));
        arrNest3.add(new model_inner("Computer",Fname));
        arrNest3.add(new model_inner("Sports",Fname));

        arrNest4.add(new model_inner("Maths",Fname));
        arrNest4.add(new model_inner("Science",Fname));
        arrNest4.add(new model_inner("English",Fname));
        arrNest4.add(new model_inner("Computer",Fname));
        arrNest4.add(new model_inner("Sports",Fname));

        arrNest5.add(new model_inner("Maths",Fname));
        arrNest5.add(new model_inner("Science",Fname));
        arrNest5.add(new model_inner("English",Fname));
        arrNest5.add(new model_inner("Computer",Fname));
        arrNest5.add(new model_inner("Sports",Fname));

        arrNest6.add(new model_inner("Maths",Fname));
        arrNest6.add(new model_inner("Science",Fname));
        arrNest6.add(new model_inner("English",Fname));
        arrNest6.add(new model_inner("Computer",Fname));
        arrNest6.add(new model_inner("Sports",Fname));


        arrOuter.add(new model_outer(arrNest1,"720"));
        arrOuter.add(new model_outer(arrNest2,"721"));
        arrOuter.add(new model_outer(arrNest3,"724"));
        arrOuter.add(new model_outer(arrNest4,"756"));
        arrOuter.add(new model_outer(arrNest5,"810"));
        arrOuter.add(new model_outer(arrNest6,"811"));





        adapter_outer = new Adapter_Outer(arrOuter);

        for (model_outer model : adapter_outer.arrOuter){
            Fname=model.getHeadingName();
        }
        vRV.setAdapter(adapter_outer);

        return view;
    }
}