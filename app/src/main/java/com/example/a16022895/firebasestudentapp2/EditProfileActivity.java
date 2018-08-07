package com.example.a16022895.firebasestudentapp2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    private static final String TAG = "EditProfileActivity";

    private EditText etName,etContactNo, etHobbies;
    private TextView tvEmail;
    private Button btnUpdate, btnDelete;
    private UserProfile userProfile;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference firebaseRef;
    private DatabaseReference userProfileRef;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser fireBaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        fireBaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        userProfileRef = firebaseDatabase.getReference("/profiles" + fireBaseUser);



        etName = (EditText) findViewById(R.id.editTextName);
        etContactNo = (EditText) findViewById(R.id.editTextContactNo);
        etHobbies = (EditText) findViewById(R.id.editTextHobbies);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);

        tvEmail = (TextView)findViewById(R.id.textViewEmail);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String contactNo = etContactNo.getText().toString().trim();
                String hoobies  = etHobbies.getText().toString().trim();
                String id = fireBaseUser.getUid();
                //TODO: Update Student record based on input given
                UserProfile user = new UserProfile(id,name,contactNo,hoobies);
                firebaseRef.child(id).setValue(user);
                Toast.makeText(getApplicationContext(), "user record updated successfully", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}
