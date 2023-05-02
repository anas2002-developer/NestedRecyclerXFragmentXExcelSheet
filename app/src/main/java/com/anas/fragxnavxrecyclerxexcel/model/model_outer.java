package com.anas.fragxnavxrecyclerxexcel.model;

import com.anas.fragxnavxrecyclerxexcel.model.model_inner;

import java.util.ArrayList;

public class model_outer {

    ArrayList<model_inner> arrNestContainer;
    String HeadingName;
    boolean isExpandable;

    public model_outer(ArrayList<model_inner> arrNestContainer, String headingName) {
        this.arrNestContainer = arrNestContainer;
        HeadingName = headingName;
        isExpandable=false;
    }

    public ArrayList<model_inner> getArrNestContainer() {
        return arrNestContainer;
    }

    public void setArrNestContainer(ArrayList<model_inner> arrNestContainer) {
        this.arrNestContainer = arrNestContainer;
    }

    public String getHeadingName() {
        return HeadingName;
    }

    public void setHeadingName(String headingName) {
        HeadingName = headingName;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}

