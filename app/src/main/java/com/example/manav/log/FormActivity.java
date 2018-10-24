package com.example.manav.log;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manav.log.fragments.home;
import com.example.manav.log.subActivities.Faq1;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class FormActivity extends AppCompatActivity {


    private String ling="";
    private String title;
    private RadioGroup gender;
    private Button btnContinue;
    private EditText name ,address,phone,email,age,language,passportnum;
    private TextView dob,doi;
    private DatePickerDialog.OnDateSetListener mDateSetListener,mdoilistner;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);

        gender=findViewById(R.id.GenericGender1);
        name = findViewById(R.id.GenericName1);
        address = findViewById(R.id.GenericAddress1);
        phone=findViewById(R.id.GenericPhone1);
        email=findViewById(R.id.GenericEmail1);
        dob=findViewById(R.id.GenericDate1);
        doi=findViewById(R.id.GenericDateofIssue11);
        age=findViewById(R.id.GenericAge1);
        language=findViewById(R.id.GenericLanguage1);
        passportnum=findViewById(R.id.GenericQualification1);
        btnContinue=findViewById(R.id.BAsicInfoContinue);

         gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rb=(RadioButton)findViewById(checkedId);
                Toast.makeText(getApplicationContext(),rb.getText(), Toast.LENGTH_SHORT).show();
                ling=rb.getText().toString();

            }
        });



        dob.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        FormActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        doi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        FormActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mdoilistner,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
//                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                dob.setText(date);
            }
        };

        mdoilistner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
//                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                doi.setText(date);
            }
        };


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData(v);

            }


        });
    }

    private void sendData(View view) {


        if(name.getText().length()<=0){
            Snackbar.make(view, "Please Enter The Name", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return;
        }

        if(address.getText().length()<=0){
            Snackbar.make(view, "Please Enter the Address", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return;
        }

        if(phone.getText().length()<=0){
            Snackbar.make(view, "Please Enter the phone Number", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return;
        }

        if(ling.length()<=0){
            Snackbar.make(view, "Please Choose the Gender", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return;
        }
       if(dob.getText().length()<=0){
            Snackbar.make(view, "Please Enter the Date Of Birth", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return;
        }if(email.getText().length()<=0){
            Snackbar.make(view, "Please Enter the Correct Email Id", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return;
        }if(age.getText().length()<=0){
            Snackbar.make(view, "Please Enter the Age", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return;
        }if(language.getText().length()<=0){
            Snackbar.make(view, "Please Enter the Language", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return;
        }if(passportnum.getText().length()<=0){
            Snackbar.make(view, "Please Enter the Passport Number", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return;
        }if(doi.getText().length()<=0){
            Snackbar.make(view, "Please Enter the Date of Issue of Passport", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return;
        }

//        FormModal model = new FormModal(name.getText().toString(),address.getText().toString(),phone.getText().toString(),email.getText().toString(),dob.getText().toString(),age.getText().toString(),language.getText().toString());
        FormModal model = new FormModal(name.getText().toString(),address.getText().toString(),phone.getText().toString(),email.getText().toString(),dob.getText().toString(),age.getText().toString(),language.getText().toString(),"ling",passportnum.getText().toString(),doi.getText().toString());
        databaseReference.child(databaseReference.push().getKey()).setValue(model);
        Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_LONG).show();

    }


}
