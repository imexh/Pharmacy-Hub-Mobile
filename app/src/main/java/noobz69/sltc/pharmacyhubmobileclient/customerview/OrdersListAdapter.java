package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.classes.Medicine;
import noobz69.sltc.pharmacyhubmobileclient.classes.Order;
import noobz69.sltc.pharmacyhubmobileclient.classes.Pharmacy;

public class OrdersListAdapter extends RecyclerView.Adapter<OrdersItemViewHolder>{
    List<Order> orderList;

    public OrdersListAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrdersItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_item_layout, parent, false);
        return new OrdersItemViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersItemViewHolder holder, int position) {
        String orderId = orderList.get(position).getOrderId();
        String pharmacyId = orderList.get(position).getPharmacyId();
        String medicineId = orderList.get(position).getMedicineId();
        String qty = orderList.get(position).getQuantity();

        FirestoreInitializer firestoreInitializer = new FirestoreInitializer();
        firestoreInitializer.getFirestore().collection("pharmacyAccounts").whereEqualTo("registrationId", pharmacyId)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Pharmacy pharmacy = document.toObject(Pharmacy.class);
                        String pharmacyName = pharmacy.getPharmacyName();

                        FirestoreInitializer firestoreInitializer1 = new FirestoreInitializer();
                        firestoreInitializer1.getFirestore().collection("medicineDetails").whereEqualTo("medicineId", medicineId)
                                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful())
                                {
                                    for (QueryDocumentSnapshot document1 : task.getResult()) {
                                        Medicine medicine = document1.toObject(Medicine.class);
                                        String medicineName = medicine.getMedicineName();

                                        holder.pharmacyName.setText("Pharmacy: " + pharmacyName);
                                        holder.orderId.setText("Order ID: " + orderId);
                                        holder.item.setText("Item: " + medicineName + " x " + qty);
                                    }
                                }
                            }
                        });


                    }
                }
            }
        });

//        String pharmacyName =
//        String medicineName =
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
