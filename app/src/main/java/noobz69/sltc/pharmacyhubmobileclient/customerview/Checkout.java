package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Random;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.basic.Login;
import noobz69.sltc.pharmacyhubmobileclient.classes.CustomerCart;
import noobz69.sltc.pharmacyhubmobileclient.classes.Medicine;
import noobz69.sltc.pharmacyhubmobileclient.classes.Order;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class Checkout extends ToolbarNavigation {

    Button checkoutBtn;
    EditText name, contact, address1, address2, city;
    String Contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkoutBtn = findViewById(R.id.placeOrderBtnCheckout);
        name = findViewById(R.id.nameEditTextPrescDetails);
        contact = findViewById(R.id.ageEditTextPrescDetails);
        address1 = findViewById(R.id.address1EditTextCheckout);
        address2 = findViewById(R.id.emailEditTextPrescDetails);
        city = findViewById(R.id.cityEditTextCheckout);

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                Contact = contact.getText().toString();
                String Address1 = address1.getText().toString();
                String Address2 = address2.getText().toString();
                String City = city.getText().toString();

                if (TextUtils.isEmpty(Contact))
                {
                    contact.setError("Mandatory field!");
                }
                else
                {
                    FirestoreInitializer firestoreInitializer = new FirestoreInitializer();
                    firestoreInitializer.getFirestore().collection("customerCart").whereEqualTo("customerUsername", Login.UserId)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    CustomerCart cart = document.toObject(CustomerCart.class);
                                    FirestoreInitializer firestoreInitializer1 = new FirestoreInitializer();
                                    firestoreInitializer1.getFirestore().collection("medicineDetails").whereEqualTo("medicineId", cart.getMedicineId())
                                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    Order order = new Order();
                                                    Medicine medicine = document.toObject(Medicine.class);
                                                    Random random = new Random();
                                                    String ID = String.valueOf(random.nextInt(99999 - 10000 + 1) + 10000);                                                    order.setOrderId(ID);
                                                    order.setPharmacyId(medicine.getPharmacyId());
                                                    order.setCustomerUsername(Login.UserId);
                                                    order.setCustomerContact(Contact);
                                                    order.setMedicineId(cart.getMedicineId());
                                                    order.setQuantity(cart.getQuantity());

                                                    firestoreInitializer1.getFirestore().collection("ordersFromCustomers").document(ID).set(order);

                                                    clear();
                                                }

                                            }
                                        }
                                    });
                                    firestoreInitializer1.getFirestore().collection("customerCart").document(cart.getId()).delete();

                                }
                            }
                        }
                    });
                    Toast.makeText(getApplicationContext(), "Order placed successfully!", Toast.LENGTH_SHORT).show();
                    Cart.activity.finish();
                    Intent intent = new Intent(Checkout.this, Homepage.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void clear()
    {
        name.getText().clear();
        contact.getText().clear();
        address1.getText().clear();
        address2.getText().clear();
        city.getText().clear();
    }
}