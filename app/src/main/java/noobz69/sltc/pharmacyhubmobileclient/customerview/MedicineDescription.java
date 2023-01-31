package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Random;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.basic.Login;
import noobz69.sltc.pharmacyhubmobileclient.classes.CustomerCart;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class MedicineDescription extends ToolbarNavigation {
    TextView medicineTitle, medicineName, price, quantity, description, sideeffects;
    ImageView image;
    Button plusBtn, minusBtn, addToCartBtn;
    int qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_description);

        qty = 1;
        String url = "https://www.healthguard.lk/pub/media/Home/HG-home-prescription-banner_1_.jpg";


        medicineTitle = findViewById(R.id.medicineTitleMedicineDesc);
        image = findViewById(R.id.medicineImageMedicineDesc);
        medicineName = findViewById(R.id.medicineNameMedicineDesc);
        price = findViewById(R.id.medicinePriceMedicineDesc);
        plusBtn = findViewById(R.id.plusBtnMedicineDesc);
        minusBtn = findViewById(R.id.minusBtnMedicineDesc);
        addToCartBtn = findViewById(R.id.addToCartBtnMedicineDesc);
        quantity = findViewById(R.id.quantityMedicineDesc);
        description = findViewById(R.id.descriptionMedicineDesc);
        sideeffects = findViewById(R.id.sideEffectsMedicineDesc);

        medicineTitle.setText(MedicineListAdapter.medicineDetail.getMedicineName());
        medicineName.setText(MedicineListAdapter.medicineDetail.getMedicineName());
        price.setText("Rs " + MedicineListAdapter.medicineDetail.getPrice() + ".00");
        description.setText(MedicineListAdapter.medicineDetail.getHowToUse());
        sideeffects.setText(MedicineListAdapter.medicineDetail.getSideEffects());
        quantity.setText(String.valueOf(qty));

        FirestoreInitializer firestoreInitializer = new FirestoreInitializer();

        if (MedicineListAdapter.medicineDetail.getImageUrl() == null)
        {
            Glide.with(this).load(url).into(image);
        }
        else
        {
            Glide.with(this).load(MedicineListAdapter.medicineDetail.getImageUrl()).into(image);
        }



        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty++;
                quantity.setText(String.valueOf(qty));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qty >= 2)
                {
                    qty--;
                    quantity.setText(String.valueOf(qty));
                }
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerCart cart = new CustomerCart();
                cart.setMedicineId(MedicineListAdapter.medicineDetail.getMedicineId());
                cart.setCustomerUsername(Login.UserId);
                cart.setQuantity(String.valueOf(qty));
                cart.setMedicineName(MedicineListAdapter.medicineDetail.getMedicineName());
                cart.setPrice(MedicineListAdapter.medicineDetail.getPrice());
                Random random = new Random();
                String ID = String.valueOf(random.nextInt(99999 - 10000 + 1) + 10000);
                cart.setId(ID);
                firestoreInitializer.getFirestore().collection("customerCart").document(ID).set(cart);
                Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}