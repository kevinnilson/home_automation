//THIS DOESN'T WORK.  I get thread error, because app engin can't make threads

package com.javaclimber.iotled;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



@SuppressWarnings("serial")
public class FirebaseLedServletAPI extends HttpServlet {

//    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
//    private DatabaseReference mRef = mDatabase.getReference();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("Hello, world");


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(getServletContext().getResourceAsStream("/WEB-INF/PRIVATE_KEY_FILE"))
                .setDatabaseUrl("https://ledcolor-c7c49.firebaseio.com/")
                .build();


        try {
            FirebaseApp.initializeApp(options);
        } catch (Exception error) {
            error.printStackTrace();
        }

        try {
            FirebaseApp.getInstance();
        } catch (Exception error) {
            error.printStackTrace();
        }

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        try {
            db.setPersistenceEnabled(true);
        } catch (Exception error) {
            error.printStackTrace();
        }


        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("/red");
        mRef.setValue(true);

//        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
//        mRef.child("red").setValue(true);
//        mRef.child("green").setValue(true);
//        mRef.child("blue").setValue(true);


//    FirebaseOptions options = new FirebaseOptions.Builder()
//            .setServiceAccount(getServletContext().getResourceAsStream("/WEB-INF/[PRIVATE_KEY_FILE]"))
//            .setDatabaseUrl("https://[FIREBASE_PROJECT_ID].firebaseio.com/")
//            .build();
//
//        try {
//            FirebaseApp.getInstance();
//        }
//        catch (Exception error){
//            //Log.info("doesn't exist...");
//        }
//
//        try {
//            FirebaseApp.initializeApp(options);
//        }
//        catch(Exception error){
//            //Log.info("already exists...");
//        }
//
//        // As an admin, the app has access to read and write all data, regardless of Security Rules
//        DatabaseReference ref = FirebaseDatabase
//                .getInstance()
//                .getReference("todoItems");
//
//        // This fires when the servlet first runs, returning all the existing values
//        // only runs once, until the servlet starts up again.
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Object document = dataSnapshot.getValue();
//                //Log.info("new value: "+ document);
//
//                String todoText = "Don't forget to...\n\n";
//
//                Iterator<DataSnapshot> children = dataSnapshot.getChildren().iterator();
//
//                while(children.hasNext()){
//                    DataSnapshot childSnapshot = (DataSnapshot) children.next();
//                    todoText = todoText + " * " + childSnapshot.getValue().toString() + "\n";
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error){
//                System.out.println("Error: "+error);
//            }
//        });

    }
}
