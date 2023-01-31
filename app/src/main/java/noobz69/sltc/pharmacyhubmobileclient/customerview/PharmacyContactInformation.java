package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class PharmacyContactInformation extends ToolbarNavigation {
    TextView pharmacyName;
    Button chatBtn;
    TextView owner, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_contact_information);

        pharmacyName = findViewById(R.id.pharmacyTitlePharmacyContacts);
        chatBtn = findViewById(R.id.chatBtnCotactInfoPharmacy);
        phone = findViewById(R.id.contactNoPharmacyInfo);
        owner = findViewById(R.id.ownerPharmacyInfo);
        address = findViewById(R.id.addressPharmacyInfo);

        pharmacyName.setText(PharmacyMenu.pharmacyDetails.getPharmacyName());
        phone.setText(PharmacyMenu.pharmacyDetails.getContactNo());
        owner.setText(PharmacyMenu.pharmacyDetails.getOwnerName());
        String Address = PharmacyMenu.pharmacyDetails.getCity() + " | " + PharmacyMenu.pharmacyDetails.getCity() + " | " + PharmacyMenu.pharmacyDetails.getProvince();
        address.setText(Address);

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Under construction", Toast.LENGTH_SHORT).show();
            }
        });
    }
}