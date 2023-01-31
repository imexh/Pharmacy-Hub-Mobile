package noobz69.sltc.pharmacyhubmobileclient;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreInitializer {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public FirebaseFirestore getFirestore() {
        return firestore;
    }
}
