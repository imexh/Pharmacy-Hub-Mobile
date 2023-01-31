package noobz69.sltc.pharmacyhubmobileclient;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import noobz69.sltc.pharmacyhubmobileclient.classes.CustomerAccount;

public class FirebaseConnection {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private boolean status;
    Map<String, Object> customerAccounts;

    public void addCustomerAccount(String username, CustomerAccount account)
    {
        firestore.collection("customerAccounts").document(username).set(account);
    }

    public Map getCustomerAccount(String username)
    {
        DocumentReference documentReference = firestore.collection("customerAccounts").document(username);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null)
                {
                    System.out.println("error");
                    return;
                }

                if (value != null && value.exists())
                {
                    customerAccounts = new HashMap<>(value.getData());
//                    System.out.println(customerAccounts);
                }
                else
                {
                    System.out.println("not found!");
                }
            }
        });
        return  customerAccounts;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
