package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.app.Activity;
import android.os.Bundle;

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
import noobz69.sltc.pharmacyhubmobileclient.classes.Order;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class OrdersPage extends ToolbarNavigation {
    RecyclerView rview;
    List<Order> orderList;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_page);
        activity = this;

        rview = (RecyclerView) findViewById(R.id.rv_orders);
        orderList = new ArrayList<>();

        FirestoreInitializer firestoreInitializer = new FirestoreInitializer();

        firestoreInitializer.getFirestore().collection("ordersFromCustomers").whereEqualTo("customerUsername", Login.UserId)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Order order = document.toObject(Order.class);
                        orderList.add(order);
                    }
                    setAdapter();
                }
            }
        });


    }

    private void setAdapter()
    {
        OrdersListAdapter adapter = new OrdersListAdapter(orderList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layoutManager);
        rview.setItemAnimator(new DefaultItemAnimator());
        rview.setAdapter(adapter);
    }
}