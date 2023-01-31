package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.classes.Medicine;
import noobz69.sltc.pharmacyhubmobileclient.classes.Pharmacy;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class PharmacyMedicines extends ToolbarNavigation {

    TextView pharmacyTitle;
    RecyclerView rview;
    List<Medicine> medicineList;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_medicines);
        activity = this;

        pharmacyTitle = findViewById(R.id.pharmacyTitleMedicineView);
        rview = (RecyclerView) findViewById(R.id.rv_medicines);
        medicineList = new ArrayList<>();

        pharmacyTitle.setText(PharmacyMenu.pharmacyDetails.getPharmacyName());

        FirestoreInitializer firestoreInitializer = new FirestoreInitializer();

        firestoreInitializer.getFirestore().collection("medicineDetails").whereEqualTo("pharmacyId", PharmacyMenu.pharmacyDetails.getRegistrationId())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Medicine medicine = document.toObject(Medicine.class);
                        medicineList.add(medicine);
                    }
                    setAdapter();
                }
            }
        });
    }

    private void setAdapter()
    {
        MedicineListAdapter adapter = new MedicineListAdapter(medicineList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layoutManager);
        rview.setItemAnimator(new DefaultItemAnimator());
        rview.setAdapter(adapter);
    }
}