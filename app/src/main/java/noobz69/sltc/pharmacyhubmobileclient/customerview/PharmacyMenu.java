package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.classes.Pharmacy;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class PharmacyMenu extends ToolbarNavigation {
    TextView pharmacyTitle;
    ImageView medicineBtn, groceriesBtn, contactBtn;
    public static Pharmacy pharmacyDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_menu);

        pharmacyTitle = findViewById(R.id.pharmacyTitlePharmacyMenu);
        medicineBtn = findViewById(R.id.mmeed);
        groceriesBtn = findViewById(R.id.sssaa);
        contactBtn = findViewById(R.id.saaww);

        FirestoreInitializer firestoreInitializer = new FirestoreInitializer();
        DocumentReference documentReference = firestoreInitializer.getFirestore().collection("pharmacyAccounts").document(PharmacyListAdapter.pharmacyId);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null)
                {
                    Toast.makeText(getApplicationContext(), "DB Error", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (value != null && value.exists())
                {
                    Pharmacy pharmacy = value.toObject(Pharmacy.class);
                    pharmacyDetails = pharmacy;
                    pharmacyTitle.setText(pharmacy.getPharmacyName());
                }
            }
        });

        medicineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PharmacyMenu.this, PharmacyMedicines.class);
                startActivity(intent);
            }
        });

        groceriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Homepage.this, Signup.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Under construction", Toast.LENGTH_SHORT).show();

            }
        });

        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PharmacyMenu.this, PharmacyContactInformation.class);
                startActivity(intent);
            }
        });



    }
}