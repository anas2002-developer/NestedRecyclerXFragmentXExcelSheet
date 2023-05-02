package com.anas.fragxnavxrecyclerxexcel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class StudListActivity extends AppCompatActivity {

    RecyclerView vRV;
    Button btnRV;
    ArrayList<Model_Stud> arrStud;
    ArrayList<Model_Stud> arrStud2;
    //    = new ArrayList<>();
    Adapter_Stud adapter_stud;

    File filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_list);




        vRV=findViewById(R.id.vRV);
        btnRV=findViewById(R.id.btnRV);
        vRV.setLayoutManager(new LinearLayoutManager(StudListActivity.this));

        LoadRecycler();

        adapter_stud = new Adapter_Stud(this,arrStud);
        vRV.setAdapter(adapter_stud);




        btnRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                creationOfDialog();
            }
        });


        ActivityCompat.requestPermissions(StudListActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}
                , PackageManager.PERMISSION_GRANTED);
        findViewById(R.id.fbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filePath = new File(Environment.getExternalStorageDirectory() + "/" + "720_MATHS"+ ".xls");


                if (!filePath.exists()) {
                    createFile();
                }

                else{
                    updateFile();

                }


            }
        });
    }


    private void creationOfDialog() {
        Dialog dialogCreate=new Dialog(StudListActivity.this);
        dialogCreate.setContentView(R.layout.create_dialog);

        EditText eName,eUid;
        Button btnAction;
        eName=dialogCreate.findViewById(R.id.eName);
        eUid=dialogCreate.findViewById(R.id.eUid);
        btnAction=dialogCreate.findViewById(R.id.btnAction);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name="";
                String uid="";

                if (eName.getText().toString().equals("")){
                    Toast.makeText(StudListActivity.this,"Enter name",Toast.LENGTH_SHORT).show();
                }
                else {
                    name=eName.getText().toString();
                }
                if(eUid.getText().toString().equals("")){
                    Toast.makeText(StudListActivity.this,"Enter uid",Toast.LENGTH_SHORT).show();
                }
                else{
                    uid=eUid.getText().toString();
                }

                arrStud.add(new Model_Stud(name, uid));
                adapter_stud.notifyItemInserted(arrStud.size()-1);
                vRV.scrollToPosition(arrStud.size()-1);

                save();
                dialogCreate.dismiss();
            }
        });
        dialogCreate.show();
    }



    private void createFile() {

        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            HSSFSheet hssfSheet = hssfWorkbook.createSheet("MATHS");

            int firstRowNum = -1;

            if (adapter_stud.arrStud_present.size()<1){
                Toast.makeText(StudListActivity.this, "Mark atleast 1 student", Toast.LENGTH_SHORT).show();
            }
            else {

                for (Model_Stud model_stud : adapter_stud.arrStud_present){
                    HSSFRow hssfRow = hssfSheet.createRow(++firstRowNum);
                    hssfRow.createCell(0).setCellValue(model_stud.getStud_name());
                    hssfRow.createCell(1).setCellValue(model_stud.getStud_uid());
                    hssfRow.createCell(2).setCellValue("Present");
                }
                for (Model_Stud model_stud : adapter_stud.arrStud_absent){
                    HSSFRow hssfRow = hssfSheet.createRow(++firstRowNum);
                    hssfRow.createCell(0).setCellValue(model_stud.getStud_name());
                    hssfRow.createCell(1).setCellValue(model_stud.getStud_uid());
                    hssfRow.createCell(2).setCellValue("Absent");
                }


                filePath.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                hssfWorkbook.write(fileOutputStream);
                Toast.makeText(StudListActivity.this, "File Createda", Toast.LENGTH_SHORT).show();

                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void updateFile(){

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet hssfSheet = hssfWorkbook.getSheet("MATHS");
            int lastRowNum = hssfSheet.getLastRowNum();

            if (adapter_stud.arrStud_present.size()<1){
                Toast.makeText(StudListActivity.this, "Mark atleast 1 student", Toast.LENGTH_SHORT).show();
            }
            else {
                for (Model_Stud model_stud : adapter_stud.arrStud_present){
                    HSSFRow hssfRow = hssfSheet.createRow(++lastRowNum);
                    hssfRow.createCell(0).setCellValue(model_stud.getStud_name());
                    hssfRow.createCell(1).setCellValue(model_stud.getStud_uid());
                    hssfRow.createCell(2).setCellValue("Present");
                }
                for (Model_Stud model_stud : adapter_stud.arrStud_absent){
                    HSSFRow hssfRow = hssfSheet.createRow(++lastRowNum);
                    hssfRow.createCell(0).setCellValue(model_stud.getStud_name());
                    hssfRow.createCell(1).setCellValue(model_stud.getStud_uid());
                    hssfRow.createCell(2).setCellValue("Absent");
                }


                fileInputStream.close();

                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                hssfWorkbook.write(fileOutputStream);
                Toast.makeText(StudListActivity.this, "File Updated", Toast.LENGTH_SHORT).show();
                fileOutputStream.close();

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void LoadRecycler(){
        SharedPreferences sp=getSharedPreferences("attendance",MODE_PRIVATE);
        Gson gson=new Gson();
        String json = sp.getString("attendee",null);
        Type type = new TypeToken<ArrayList<Model_Stud>>(){}.getType();
        arrStud = gson.fromJson(json,type);

        if (arrStud == null){
            arrStud = new ArrayList<>();
            arrStud.add(new Model_Stud(R.drawable.ic_launcher_background,"Anas","21BCS8965"));
            arrStud.add(new Model_Stud(R.drawable.ic_launcher_background,"Shruti","21BCS8965"));
            arrStud.add(new Model_Stud(R.drawable.ic_launcher_background,"Shreya","21BCS8965"));
            arrStud.add(new Model_Stud(R.drawable.ic_launcher_background,"Palki","21BCS8965"));
            arrStud.add(new Model_Stud(R.drawable.ic_launcher_background,"Muskan","21BCS8965"));
            arrStud.add(new Model_Stud(R.drawable.ic_launcher_background,"Priyanshi","21BCS8965"));
            arrStud.add(new Model_Stud(R.drawable.ic_launcher_background,"Karishma","21BCS8965"));
            arrStud.add(new Model_Stud(R.drawable.ic_launcher_background,"Anabia","21BCS8965"));
            arrStud.add(new Model_Stud(R.drawable.ic_launcher_background,"Mahira","21BCS8965"));
            arrStud.add(new Model_Stud(R.drawable.ic_launcher_background,"Ananya","21BCS8965"));
            arrStud.add(new Model_Stud(R.drawable.ic_launcher_background,"Ashmi","21BCS8965"));

        }
    }

    private void save(){
        SharedPreferences sp = getSharedPreferences("attendance",MODE_PRIVATE);
        SharedPreferences.Editor sp_editor = sp.edit();
        Gson gson=new Gson();
        String json = gson.toJson(arrStud);
        sp_editor.putString("attendee",json);
        sp_editor.apply();
        Toast.makeText(StudListActivity.this, "File Saved", Toast.LENGTH_SHORT).show();
    }
}