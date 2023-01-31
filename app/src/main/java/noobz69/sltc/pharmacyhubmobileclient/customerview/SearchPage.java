package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.classes.Medicine;
import noobz69.sltc.pharmacyhubmobileclient.classes.Pharmacy;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class SearchPage extends ToolbarNavigation {
    ImageView searchBtn, pharmacyImage, medicineImage;
    EditText searchName;
    TextView pharmacyName, medicineName;
    public static String searchText;
    public static HashMap<String, String> pharmacyDetails;
    public static HashMap<String, String> medicineDetails;
    boolean found = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        String pharmacyUrl = "https://static.vecteezy.com/system/resources/previews/007/438/687/original/illustration-of-a-pharmacy-health-pharmacy-logo-free-vector.jpg";
        String medicineUrl = "https://www.healthguard.lk/pub/media/Home/HG-home-prescription-banner_1_.jpg";

        searchBtn = findViewById(R.id.searchBtnSearch);
        searchName = findViewById(R.id.searchEditTextSearch);
        pharmacyName = findViewById(R.id.pharmacyNameLayout);
        pharmacyImage = findViewById(R.id.imageMedicineLayout);
        medicineName = findViewById(R.id.medicineNameLayout);
        medicineImage = findViewById(R.id.imgMedicineLayout);
        pharmacyDetails = new HashMap<>();
        medicineDetails = new HashMap<>();
        FirestoreInitializer firestoreInitializer = new FirestoreInitializer();
        firestoreInitializer.getFirestore().collection("pharmacyAccounts").
                get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Pharmacy pharmacy = document.toObject(Pharmacy.class);

                        if (pharmacy.getImageUrl() == null)
                        {
                            Glide.with(SearchPage.this).load(pharmacyUrl).into(pharmacyImage);
                        }
                        else
                        {
                            Glide.with(SearchPage.this).load(pharmacy.getImageUrl()).into(pharmacyImage);
                        }
                        pharmacyName.setText(pharmacy.getPharmacyName());
                        break;
                    }
                }
            }
        });

        firestoreInitializer.getFirestore().collection("medicineDetails").
                get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Medicine medicine = document.toObject(Medicine.class);

                        if (medicine.getImageUrl() == null)
                        {
                            Glide.with(SearchPage.this).load(medicineUrl).into(medicineImage);
                        }
                        else
                        {
                            Glide.with(SearchPage.this).load(medicine.getImageUrl()).into(medicineImage);
                        }
                        medicineName.setText(medicine.getMedicineName());
                        break;
                    }
                }
            }
        });



        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SearchText = searchName.getText().toString().toLowerCase();
                if (TextUtils.isEmpty(SearchText))
                {
                    searchName.setError("Cannot be empty!");
                }
                else
                {
                    FirestoreInitializer firestoreInitializer1 = new FirestoreInitializer();
                    searchText = SearchText;
                    firestoreInitializer1.getFirestore().collection("pharmacyAccounts").
                            get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Pharmacy pharmacy = document.toObject(Pharmacy.class);

                                    if(pharmacy.getPharmacyName().toLowerCase().equals(searchText) || pharmacy.getRegistrationId().toLowerCase().equals(searchText))
                                    {
                                        found = true;
                                        pharmacyDetails.put("pharmacyName", pharmacy.getPharmacyName());
                                        if (pharmacy.getImageUrl() != null)
                                        {
                                            pharmacyDetails.put("imageUrl", pharmacy.getImageUrl());
                                        }
                                        Intent intent = new Intent(SearchPage.this, SearchResults.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        FirestoreInitializer firestoreInitializer2 = new FirestoreInitializer();
                                        firestoreInitializer2.getFirestore().collection("medicineDetails").
                                                get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document1 : task.getResult()) {
                                                        Medicine medicine = document1.toObject(Medicine.class);
                                                        if (medicine.getMedicineName().toLowerCase().equals(searchText) || medicine.getMedicineId().toLowerCase().equals(searchText))
                                                        {
                                                            found = true;
                                                            medicineDetails.put("medicineName", medicine.getMedicineName());
                                                            if (medicine.getImageUrl() != null)
                                                            {
                                                                medicineDetails.put("imageUrl", medicine.getImageUrl());

                                                            }
                                                            Intent intent = new Intent(SearchPage.this, SearchResults.class);
                                                            startActivity(intent);
                                                        }
                                                    }
                                                }
                                            }
                                                });
                                    }
                                }
                                if (!found)
                                {
                                    Toast.makeText(getApplicationContext(), "No result!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                    searchName.getText().clear();
                }
            }
        });
    }
}