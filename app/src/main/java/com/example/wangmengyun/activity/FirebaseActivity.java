//package com.example.wangmengyun.activity;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.text.Editable;
//import android.text.InputFilter;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.example.wangmengyun.lefei.R;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by wangmengyun on 2018/3/22.
// */
//
//public class FirebaseActivity extends Activity {
//
//    public static final String ANONYMOUS = "anonymous";
//    private static final String TAG = "FirebaseActivity";
//
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference mCityDatabaseReference;
//
//    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
//
//
//    private ListView mMessageListView;
//    private MessageAdapter mMessageAdapter;
//    private ProgressBar mProgressBar;
//    private ImageButton mPhotoPickerButton;
//    private EditText mMessageEditText;
//    private Button mSendButton;
//    private FirebaseAuth mAuth;
//    private String mUsername;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mAuth = FirebaseAuth.getInstance();
//
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//
//                        // ...
//                    }
//                });
//
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mCityDatabaseReference = mFirebaseDatabase.getReference().child("departureCity");
//
//        setContentView(R.layout.activity_firebase);
//
//
//        mUsername = ANONYMOUS;
//
//            // Initialize references to views
//            mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
//            mMessageListView = (ListView) findViewById(R.id.messageListView);
//            mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
//            mMessageEditText = (EditText) findViewById(R.id.messageEditText);
//            mSendButton = (Button) findViewById(R.id.sendButton);
//
//            // Initialize message ListView and its adapter
//            List<FriendlyMessage> friendlyMessages = new ArrayList<>();
//            mMessageAdapter = new MessageAdapter(this, R.layout.item_message, friendlyMessages);
//            mMessageListView.setAdapter(mMessageAdapter);
//
//            // Initialize progress bar
//            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
//
//            // ImagePickerButton shows an image picker to upload a image for a message
//            mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // TODO: Fire an intent to show an image picker
//                }
//            });
//
//            // Enable Send button when there's text to send
//            mMessageEditText.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                    if (charSequence.toString().trim().length() > 0) {
//                        mSendButton.setEnabled(true);
//                    } else {
//                        mSendButton.setEnabled(false);
//                    }
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//                }
//            });
//            mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
//
//            // Send button sends a message and clears the EditText
//            mSendButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // TODO: Send messages on click
//
//                    // Clear input box
//                    mMessageEditText.setText("");
//                }
//            });
//        }
//
//
//        @Override
//        public boolean onOptionsItemSelected (MenuItem item){
//            return super.onOptionsItemSelected(item);
//        }
//    }
