package com.anas.fragxnavxrecyclerxexcel;

public class Model_Stud {
    int Stud_img;
    String Stud_name,Stud_uid;

    public Model_Stud(String stud_name, String stud_uid) {
        Stud_name = stud_name;
        Stud_uid = stud_uid;
    }

    public Model_Stud(int stud_img, String stud_name, String stud_uid) {
        Stud_img = stud_img;
        Stud_name = stud_name;
        Stud_uid = stud_uid;
    }

    public int getStud_img() {
        return Stud_img;
    }

    public void setStud_img(int stud_img) {
        Stud_img = stud_img;
    }

    public String getStud_name() {
        return Stud_name;
    }

    public void setStud_name(String stud_name) {
        Stud_name = stud_name;
    }

    public String getStud_uid() {
        return Stud_uid;
    }

    public void setStud_uid(String stud_uid) {
        Stud_uid = stud_uid;
    }
}
