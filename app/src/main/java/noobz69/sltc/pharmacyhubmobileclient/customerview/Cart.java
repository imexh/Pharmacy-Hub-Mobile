package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.basic.Login;
import noobz69.sltc.pharmacyhubmobileclient.classes.CustomerCart;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class Cart extends ToolbarNavigation {
    RecyclerView rview;
    Button continueBtn, checkoutBtn;
    List<CustomerCart> cartList;
    TextView total;
    public static int sum;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        activity = this;
        sum = 0;

        continueBtn = findViewById(R.id.continueShoppingBtnCart);
        checkoutBtn = findViewById(R.id.confirmBtnPrescConfirm);
        rview = findViewById(R.id.rv_cartitems);
        total = findViewById(R.id.totalTextViewCart);
        cartList = new ArrayList<>();

        FirestoreInitializer firestoreInitializer = new FirestoreInitializer();

        firestoreInitializer.getFirestore().collection("customerCart").whereEqualTo("customerUsername", Login.UserId)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        CustomerCart cart = document.toObject(CustomerCart.class);
                        int val = Integer.parseInt(cart.getQuantity()) * Integer.parseInt(cart.getPrice());
                        sum += val;
                        cartList.add(cart);
                    }
                    total.setText("Rs." + String.valueOf(sum));
                    setAdapter();
                }
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this, Pharmacies.class);
                startActivity(intent);
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this, Checkout.class);
                startActivity(intent);
            }
        });
    }

    private void setAdapter()
    {
        CartListAdapter adapter = new CartListAdapter(cartList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layoutManager);
        rview.setItemAnimator(new DefaultItemAnimator());
        rview.setAdapter(adapter);
    }
}