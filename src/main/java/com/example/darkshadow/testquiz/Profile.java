package com.example.darkshadow.testquiz;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Profile extends AppCompatActivity {

    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        Log.d("xx", "user: "+mAuth.getUid());

        final EditText name = (EditText) findViewById(R.id.profileFullName);
        final EditText bio = (EditText) findViewById(R.id.profileBio);
        final EditText emailPhone = (EditText) findViewById(R.id.profilePhoneOrEmail);
        final TextView score = (TextView) findViewById(R.id.profileScore);
        final TextView rank = (TextView) findViewById(R.id.profileRank);
        final ImageButton submit = (ImageButton) findViewById(R.id.profileSubmitBtn);
        final de.hdodenhof.circleimageview.CircleImageView uploadProfilePic = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.profileUploadNew);

        final FirebaseUser user = mAuth.getCurrentUser();
        //filestorage
        mStorageRef = FirebaseStorage.getInstance().getReference();

        final DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("U").child(user.getUid());


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String strName = dataSnapshot.child("N").getValue().toString();
                String strBio = dataSnapshot.child("B").getValue().toString();
                String strEmailPhone = dataSnapshot.child("E").getValue().toString();
                String strScore = dataSnapshot.child("S").getValue().toString();
                
                String strRank = dataSnapshot.child("R").getValue().toString();

                name.setText(strName);
                bio.setText(strBio);
                emailPhone.setText(strEmailPhone);
                score.setText(strScore);
                rank.setText(strRank);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String n = name.getText().toString();
                        String b = bio.getText().toString();
                        String e = emailPhone.getText().toString();

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("U").child("1");
                        myRef.child("N").setValue(n);
                        myRef.child("B").setValue(b);
                        myRef.child("E").setValue(e);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        uploadProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This brings up the Documents app. To allow the user to also use any gallery apps they might have installed:
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, 2);

                Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
                StorageReference riversRef = mStorageRef.child("images/rivers.jpg");

                riversRef.putFile(file)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                // ...
                            }
                        });

                Toast.makeText(Profile.this, "working", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
