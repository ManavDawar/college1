package com.example.manav.log.subActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.manav.log.ComplainModal;
import com.example.manav.log.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class complaints extends AppCompatActivity {

    List<String> list;
    Spinner spinner;
    Button btn ;
    EditText email ;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    private ImageView image;
    private Uri filepath;
    EditText complainMessage;
    ComplainModal model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        list = new ArrayList<String>();

        addChildsInList();


        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("complaints");
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference().child("complaints");

        model = new ComplainModal();

        spinner=findViewById(R.id.complaintSpinner);
        btn = findViewById(R.id.complaint_button);
        email = findViewById(R.id.emailText);
        complainMessage= findViewById(R.id.complainMessage);
        image = findViewById(R.id.complainImage);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.spinner_layout, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),list.get(position) + " ", Toast.LENGTH_LONG).show();
                model.setComplainType(list.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent,"Choose the Image"),1);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }

    private void sendData() {

        model.setComplainText(complainMessage.getText().toString());
//      model.setImage(filepath);

        databaseReference=database.getReference().child("complaint");
        model = getIntent().getParcelableExtra("model");
        databaseReference.child(databaseReference.push().getKey()).setValue(model);
    }

    private void addChildsInList() {
        list.add("Delay In application Submission");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
    }

    private void UploadImage(Uri filepath) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");

        String addressOfLink= UUID.randomUUID().toString();

        StorageReference ref = storageReference.child(addressOfLink);

        ref.putFile(filepath)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Image failed to upload,please try again", Toast.LENGTH_SHORT).show();

                    }
                }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText(getApplicationContext(), "Image uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                double progress=(100)*(taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setMessage("uploaded"+progress+"%");
            }
        });


        progressDialog.show();


    }



    private void ChooseImage(int photoRequestcode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent,"Choose the Headshot"),photoRequestcode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK&&data!=null&&data.getData()!=null){

            try {
                if(requestCode==1){
                    filepath= data.getData();
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                    image.setImageBitmap(bitmap);
                    UploadImage(filepath);
                    model.setImage(filepath.toString());

                }

            }catch (IOException e){
                Toast.makeText(getApplicationContext(),"Error!!", Toast.LENGTH_SHORT);
            }

        }
    }

}
