package com.example.manav.log;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusEnquiry extends AppCompatActivity {

    TextView passport,onlinereg,fileNo;
    Button fetchInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_enquiry);

        passport=findViewById(R.id.passportnum);
        fileNo=findViewById(R.id.FileNo);
        onlinereg=findViewById(R.id.onlineRegNo);

        fetchInfo= findViewById(R.id.fetchInfo);


        fetchInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.putExtra("passportnum",passport.getText().toString());
                intent.putExtra("FILENUM",fileNo.getText().toString());
                intent.putExtra("onlinereg",onlinereg.getText().toString());
                intent.setClass(getApplicationContext(),StatusQuery.class);
                startActivity(intent);
            }


        });


    }


}
