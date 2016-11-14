package com.javaclimber.colorledfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = mDatabase.getReference();

    private ToggleButton redToggleButton;
    private ToggleButton greenToggleButton;
    private ToggleButton blueToggleButton;

    private final static String LOG_TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redToggleButton = (ToggleButton) findViewById(R.id.redToggleButton);
        greenToggleButton = (ToggleButton) findViewById(R.id.greenToggleButton);
        blueToggleButton = (ToggleButton) findViewById(R.id.blueToggleButton);


        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildChanged(DataSnapshot snap, String s) {
                Log.d(LOG_TAG, "onChildChanged " + s);
                Log.d(LOG_TAG, "snap " + snap);

                updateView(snap);
            }


            @Override
            public void onChildAdded(DataSnapshot snap, String s) {
                Log.d(LOG_TAG, "onChildAdded = " + s);
                Log.d(LOG_TAG, "dataSnapshot = " + snap);

                updateView(snap);
            }


            @Override
            public void onChildRemoved(DataSnapshot snap) {
                Log.d(LOG_TAG, "onChildRemoved = " + snap);

                updateView(snap);
            }

            @Override
            public void onChildMoved(DataSnapshot snap, String s) {
                Log.d(LOG_TAG, "onChildMoved = " + snap);

                updateView(snap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(LOG_TAG, "onCancelled " + databaseError);
            }
        });
    }

    private void updateView(DataSnapshot snap) {
        if ("red".equals(snap.getKey()))
            redToggleButton.setChecked((Boolean) snap.getValue());

        if ("green".equals(snap.getKey()))
            greenToggleButton.setChecked((Boolean) snap.getValue());

        if ("blue".equals(snap.getKey()))
            blueToggleButton.setChecked((Boolean) snap.getValue());
    }


    public void saveColor(View view) {
        Log.d(LOG_TAG, "saveButton.onClick");

        boolean isRed = redToggleButton.isChecked();
        boolean isGreen = greenToggleButton.isChecked();
        boolean isBlue = blueToggleButton.isChecked();

        Log.d(LOG_TAG, "Saving red: " + isRed);
        Log.d(LOG_TAG, "Saving green: " + isGreen);
        Log.d(LOG_TAG, "Saving blue: " + isBlue);

        mRef.child("red").setValue(isRed);
        mRef.child("green").setValue(isGreen);
        mRef.child("blue").setValue(isBlue);
    }
}
