package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class PrescriptionDetails extends ToolbarNavigation {
    Button nextBtn;
    EditText name, age, contact, email, allergy, prescriptionLink, city, district, province;
    public static Activity activity;
    public static HashMap<String, String> formDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_details);
        activity = this;

        nextBtn = findViewById(R.id.nextBtnPrescDetails);
        name = findViewById(R.id.nameEditTextPrescDetails);
        age = findViewById(R.id.ageEditTextPrescDetails);
        contact = findViewById(R.id.contactNoEditTextPrescDetails);
        email = findViewById(R.id.emailEditTextPrescDetails);
        allergy = findViewById(R.id.allergyEditTextPrescDetails);
        prescriptionLink = findViewById(R.id.prescLinkEditTextPrescDetails);
        city = findViewById(R.id.cityEditTextPrescDetails);
        province = findViewById(R.id.provinceEditTextPrescDetails);
        district = findViewById(R.id.districtEditTextPrescDetails);
        formDetails = new HashMap<>();


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Age = age.getText().toString();
                String Contact = contact.getText().toString();
                String Email = email.getText().toString();
                String Allergy = allergy.getText().toString();
                String Link = prescriptionLink.getText().toString();
                String City = city.getText().toString();
                String Province = province.getText().toString();
                String District = district.getText().toString();

                if (isNotEmpty(Name, Age, Contact, Email, Allergy, Link, District, Province, City))
                {
                    formDetails.put("name", Name);
                    formDetails.put("age", Age);
                    formDetails.put("contact", Contact);
                    formDetails.put("email", Email);
                    formDetails.put("allergy", Allergy);
                    formDetails.put("link", Link);
                    formDetails.put("district", District);
                    formDetails.put("province", Province);
                    formDetails.put("city", City);

                    Intent intent = new Intent(PrescriptionDetails.this, PrescriptionConfirm.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isNotEmpty(String Name, String Age, String Contact, String Email, String Allergy, String Link,
                               String District, String Province, String City)
    {
        if (TextUtils.isEmpty(Name))
        {
            name.setError("Enter name!");
            return false;
        }
        else if (TextUtils.isEmpty(Age))
        {
            age.setError("Enter age!");
            return false;
        }
        else if (TextUtils.isEmpty(Contact))
        {
            contact.setError("Enter Phone Number!");
            return false;
        }
        else if (TextUtils.isEmpty(Email))
        {
            email.setError("Enter Email!");
            return false;
        }
        else if (TextUtils.isEmpty(Allergy))
        {
            allergy.setError("Enter Allergy!");
            return false;
        }
        else if (TextUtils.isEmpty(Link))
        {
            prescriptionLink.setError("Enter Link!");
            return false;
        }
        else if (TextUtils.isEmpty(District))
        {
            district.setError("Enter District!");
            return false;
        }
        else if (TextUtils.isEmpty(Province))
        {
            province.setError("Enter Province!");
            return false;
        }
        else if (TextUtils.isEmpty(City))
        {
            city.setError("Enter City!");
            return false;
        }
        return true;
    }
}