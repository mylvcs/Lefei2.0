//package com.example.wangmengyun.activity;
//
//import android.app.Activity;
//import android.content.DialogInterface;
//import android.graphics.Paint;
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import com.firebase.client.Firebase;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.HashMap;
//public class FirebaseActivity extends AppCompatActivity {
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//
//        Firebase.setAndroidContext(this);
//
//        Firebase ref = new Firebase("https://lefei-2018bishe.firebaseio.com");
//
//        class User {
//            private int birthYear;
//            private String fullName;
//            public User() {}
//            public User(String fullName, int birthYear) {
//                this.fullName = fullName;
//                this.birthYear = birthYear;
//            }
//            public long getBirthYear() {
//                return birthYear;
//            }
//            public String getFullName() {
//                return fullName;
//            }
//        }
////
////
////        Firebase alanRef = ref.child("users").child("alanisawesome");
////        User alan = new User("Alan Turing", 1912);
////        alanRef.setValue(alan);
//
//
//
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");
//    }
//
//}