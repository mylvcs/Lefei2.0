//package com.example.wangmengyun.activity;
//
//import android.support.annotation.NonNull;
//import android.util.Log;
//
//import com.example.wangmengyun.Bean.Flight;
//import com.google.android.gms.tasks.Continuation;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.mongodb.stitch.android.StitchClient;
//import com.mongodb.stitch.android.StitchClientFactory;
//import com.mongodb.stitch.android.auth.anonymous.AnonymousAuthProvider;
//import com.mongodb.stitch.android.services.mongodb.MongoClient;
//
//import org.bson.Document;
//
//import java.util.List;
//
///**
// * Created by wangmengyun on 2018/4/29.
// */
//
//public class AtlasActivity {
//
//    StitchClientFactory.create(AtlasActivity(), "lefei-zwksx").addOnCompleteListener(new OnCompleteListener<StitchClient>() {
//
//        @Override
//        public void onComplete(@NonNull final Task<StitchClient> task){
//            if (!task.isSuccessful()) {
//                Log.e("STITCH", "error creating Stitch client", task.getException());
//                return;
//            }
//
//            final StitchClient stitchClient = task.getResult();
//            final MongoClient mongoClient = new MongoClient(stitchClient, "mongodb-atlas");
//            final MongoClient.Collection coll = mongoClient.getDatabase("Lefei").getCollection("Flights");
//
//            stitchClient.logInWithProvider(new AnonymousAuthProvider()).continueWithTask(
//                    new Continuation<String, Task<Document>>() {
//                        @Override
//                        public Task<Document> then(@NonNull Task<String> task) throws Exception {
//                            final Document updateDoc = new Document(
//                                    "owner_id",
//                                    task.getResult()
//                            );
//
//                            updateDoc.put("number", 42);
//                            return coll.updateOne(null, updateDoc, true);
//                        }
//                    }
//            ).continueWithTask(new Continuation<Document, Task<List<Document>>>() {
//
//                @Override
//                public Task<List<Document>> then(@NonNull Task<Document> task) throws Exception {
//                    if (!task.isSuccessful()) {
//                        throw task.getException();
//                    }
//                    return coll.find(new Document("owner_id", stitchClient.getUserId()), 100);
//                }
//            }).addOnCompleteListener(new OnCompleteListener<List<Document>>() {
//                @Override
//                public void onComplete(@NonNull Task<List<Document>> task) {
//                    if (task.isSuccessful()) {
//                        Log.d("STITCH", task.getResult().toString());
//                        return;
//                    }
//                    Log.e("STITCH", task.getException().toString());
//                }
//            });
//        }
//    });
//}
//
