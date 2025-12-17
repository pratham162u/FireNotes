package com.example.firenotes;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Utility {

    public static FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public static CollectionReference getCollectionReferenceForNotes() {

        FirebaseUser user = getCurrentUser();

        if (user == null) {
            throw new IllegalStateException("User not logged in");
        }

        return FirebaseFirestore.getInstance()
                .collection("notes")
                .document(user.getUid())
                .collection("my_notes");
    }


    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
