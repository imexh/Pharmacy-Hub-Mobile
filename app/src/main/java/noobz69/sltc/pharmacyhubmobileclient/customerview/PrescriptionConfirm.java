package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.basic.Login;
import noobz69.sltc.pharmacyhubmobileclient.classes.Prescription;

public class PrescriptionConfirm extends AppCompatActivity {
    Button backBtn, confirmBtn;
    TextView name, age, contact, email, city, district, province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_confirm);

        backBtn = findViewById(R.id.backbtnPrescConfirm);
        confirmBtn = findViewById(R.id.confirmBtnPrescConfirm);
        name = findViewById(R.id.nameTextViewPrescriptionConfirmation);
        age = findViewById(R.id.ageTextViewPrescriptionConfirmation);
        contact = findViewById(R.id.contactTextViewPrescriptionConfirmation);
        email = findViewById(R.id.emailTextViewPrescriptionConfirmation);
        city = findViewById(R.id.cityTextViewPrescriptionConfirmation);
        district = findViewById(R.id.districtTextViewPrescriptionConfirmation);
        province = findViewById(R.id.provinceTextViewPrescriptionConfirmation);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText(PrescriptionDetails.formDetails.get("name"));
                age.setText(PrescriptionDetails.formDetails.get("age"));
                contact.setText(PrescriptionDetails.formDetails.get("contact"));
                email.setText(PrescriptionDetails.formDetails.get("email"));
                city.setText(PrescriptionDetails.formDetails.get("city"));
                district.setText(PrescriptionDetails.formDetails.get("district"));
                province.setText(PrescriptionDetails.formDetails.get("province"));

                FirestoreInitializer firestoreInitializer = new FirestoreInitializer();
                Random random = new Random();
                String ID = String.valueOf(random.nextInt(99999 - 10000 + 1) + 10000);
                Prescription prescription = new Prescription();
                prescription.setCustomerUsername(Login.UserId);
                prescription.setPrescriptionId(ID);
                prescription.setLink(PrescriptionDetails.formDetails.get("link"));
                prescription.setStatus("open");

                firestoreInitializer.getFirestore().collection("prescriptionDetails").document(ID).set(prescription);

                PrescriptionDetails.activity.finish();
                Intent intent = new Intent(PrescriptionConfirm.this, PrescriptionSuccessfull.class);
                startActivity(intent);
                finish();
            }
        });
    }

}