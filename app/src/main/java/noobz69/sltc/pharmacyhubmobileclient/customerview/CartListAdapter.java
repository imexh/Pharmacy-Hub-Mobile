package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.basic.Login;
import noobz69.sltc.pharmacyhubmobileclient.classes.CustomerCart;

public class CartListAdapter extends RecyclerView.Adapter<CartItemViewHolder>{
    List<CustomerCart> cartList;

    public CartListAdapter(List<CustomerCart> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new CartItemViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        String MedicineName = cartList.get(position).getMedicineName();
        String priceQty = cartList.get(position).getQuantity() + " x Rs." + cartList.get(position).getPrice();

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirestoreInitializer firestoreInitializer = new FirestoreInitializer();

                firestoreInitializer.getFirestore().collection("customerCart").document(cartList.get(position).getId()).delete();
                Intent intent = new Intent(Cart.activity, Cart.class);
                Cart.activity.finish();
                Cart.activity.startActivity(intent);
            }
        });

        holder.medicineName.setText(MedicineName);
        holder.medicinePriceQty.setText(priceQty);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
