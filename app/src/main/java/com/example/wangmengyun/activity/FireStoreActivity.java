package com.example.wangmengyun.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.wangmengyun.lefei.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import org.w3c.dom.Document;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangmengyun on 2018/4/17.
 */

public class FireStoreActivity extends AppCompatActivity {

    private String TAG = "FireStoreActivity";

    public static final String ANONYMOUS = "anonymous";
    private static final int RC_SIGN_IN = 1;


    private com.google.firebase.auth.FirebaseAuth mFirebaseAuth;

    private String mUsername;

    private ListView mMessageListView;

    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;

    private com.google.android.gms.common.SignInButton signInButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_googlelogin);


        mUsername = ANONYMOUS;

        signInButton = findViewById(R.id.sign_in_button);


        mFirebaseAuth = FirebaseAuth.getInstance();

        // Initialize references to views
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);

        FirebaseApp.initializeApp(this);


        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> user = new HashMap<>();
                user.put("first", "Ada");
                user.put("last", "Lovelace");
                user.put("born", 1815);

                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

            }
        });

    }


    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("sampleData/inspiration");


}
