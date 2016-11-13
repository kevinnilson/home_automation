package com.javaclimber.colorledfirebase;

import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = mDatabase.getReference();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button lookupButton = (Button) findViewById(R.id.lookupButton);


        final ToggleButton redToggleButton = (ToggleButton) findViewById(R.id.redToggleButton);
        final ToggleButton greenToggleButton = (ToggleButton) findViewById(R.id.greenToggleButton);
        final ToggleButton blueToggleButton = (ToggleButton) findViewById(R.id.blueToggleButton);
        final Button saveButton = (Button) findViewById(R.id.saveButton);


        mRef.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildChanged(DataSnapshot snap, String s) {
                System.out.println("onChildChanged " + s);

                System.out.println("snap " + snap);

                if ("redX".equals(snap.getKey()))
                    redToggleButton.setChecked((Boolean) snap.getValue());

                if ("greenX".equals(snap.getKey()))
                    greenToggleButton.setChecked((Boolean) snap.getValue());

                if ("blueX".equals(snap.getKey()))
                    blueToggleButton.setChecked((Boolean) snap.getValue());

//
            }


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println("onChildAdded = " + s);

                System.out.println("dataSnapshot = " + dataSnapshot);


            }


            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                System.out.println("onChildRemoved = " + dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot snap, String s) {
                System.out.println("onChildMoved = " + snap);
            }

            @Override
            public void onCancelled(DatabaseError var1) {
                System.out.println("onCancelled " + var1);
            }
        });


        lookupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("lookupButton.onCLick");

                mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snap) {

                        System.out.println("SingleValueEvent.onDataChange");
                        Map<String, Object> colorsObject = (Map<String, Object>) snap.getValue();

                        System.out.println("Colors = " + colorsObject);

                        try {
                            Map<String, String> colorsObject2 = (Map<String, String>) snap.getValue();

                            System.out.println("Colors = " + colorsObject2);
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                        redToggleButton.setChecked((Boolean) colorsObject.get("redX"));
                        greenToggleButton.setChecked((Boolean) colorsObject.get("greenX"));
                        blueToggleButton.setChecked((Boolean) colorsObject.get("blueX"));
                    }

                    @Override
                    public void onCancelled(DatabaseError var1) {
                    }
                });


            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("saveButton.onClick");

                boolean isRed = redToggleButton.isChecked();
                boolean isGreen = greenToggleButton.isChecked();
                boolean isBlue = blueToggleButton.isChecked();

                System.out.println("Saving red: " + isRed);
                System.out.println("Saving green: " + isGreen);
                System.out.println("Saving blue: " + isBlue);

                mRef.child("redX").setValue(isRed);
                mRef.child("greenX").setValue(isGreen);
                mRef.child("blueX").setValue(isBlue);

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
