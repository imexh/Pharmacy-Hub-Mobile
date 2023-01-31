package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.basic.Login;
import noobz69.sltc.pharmacyhubmobileclient.classes.Pharmacy;
import noobz69.sltc.pharmacyhubmobileclient.classes.Reminder;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class Pharmacies extends ToolbarNavigation {
    RecyclerView rview;
    List<Pharmacy> pharmacyList;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacies);
        activity = this;

        rview = (RecyclerView) findViewById(R.id.rv_pharmacies);
        pharmacyList = new ArrayList<>();

        FirestoreInitializer firestoreInitializer = new FirestoreInitializer();

        firestoreInitializer.getFirestore().collection("pharmacyAccounts")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Pharmacy pharmacy = document.toObject(Pharmacy.class);
                        pharmacyList.add(pharmacy);
                    }
                    setAdapter();
                }
            }
        });

    }

    private void setAdapter()
    {
        PharmacyListAdapter adapter = new PharmacyListAdapter(pharmacyList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layoutManager);
        rview.setItemAnimator(new DefaultItemAnimator());
        rview.setAdapter(adapter);
    }
}