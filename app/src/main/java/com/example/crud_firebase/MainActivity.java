package com.example.crud_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
Button btn_id,btn_read,btn_update;
EditText id1,name,rollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id1 = findViewById(R.id.id1);
        name = findViewById(R.id.name);
        rollno = findViewById(R.id.rollno);
        btn_id = findViewById(R.id.btn);
        btn_read = findViewById(R.id.read);
        btn_update = findViewById(R.id.update);
        btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("students");
//                myRef.setValue("Hello, World!"); //  for save as a string
                Student student = new Student(Integer.parseInt(id1.getText().toString()),rollno.getText().toString(),name.getText().toString());
          //      myRef.setValue(student);
                String cid = myRef.push().getKey();
                myRef.child(cid).setValue(student);
                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("students");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
//                        String value = dataSnapshot.getValue(String.class);
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Student student = snapshot.getValue(Student.class);
                            Toast.makeText(MainActivity.this, "" + student.getName(), Toast.LENGTH_SHORT).show();
                            //Log.d(TAG, "Value is: " + value);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError){
                    }
                });
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Update.class);
                startActivity(in);
            }
        });
    }
}
