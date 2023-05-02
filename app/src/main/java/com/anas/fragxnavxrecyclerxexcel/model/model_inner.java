package com.anas.fragxnavxrecyclerxexcel.model;

public class model_inner {

    String Name;
    String Fname;

    public model_inner(String name, String fname) {
        Name = name;
        Fname = fname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }
}
