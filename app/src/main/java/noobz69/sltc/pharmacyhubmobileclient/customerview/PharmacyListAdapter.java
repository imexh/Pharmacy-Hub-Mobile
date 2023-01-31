package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.classes.Pharmacy;

public class PharmacyListAdapter extends RecyclerView.Adapter<PharmacyItemViewHolder>{

    List<Pharmacy> pharmacies;
    public static String pharmacyId;

    public PharmacyListAdapter(List<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    @NonNull
    @Override
    public PharmacyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pharmacy_item_layout, parent, false);
        return new PharmacyItemViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyItemViewHolder holder, int position) {
        String pharmacyName = pharmacies.get(position).getPharmacyName();
        String imageUrl = "https://static.vecteezy.com/system/resources/previews/007/438/687/original/illustration-of-a-pharmacy-health-pharmacy-logo-free-vector.jpg";

        holder.pharmacyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pharmacyId = pharmacies.get(position).getRegistrationId();
                Intent intent = new Intent(view.getContext(), PharmacyMenu.class);
                view.getContext().startActivity(intent);
            }
        });

        holder.pharmacyName.setText(pharmacyName);
        if (pharmacies.get(position).getImageUrl() == null)
        {
            Glide.with(Pharmacies.activity).load(imageUrl).into(holder.pharmacyImage);
        }
        else
        {
            Glide.with(Pharmacies.activity).load(pharmacies.get(position).getImageUrl()).into(holder.pharmacyImage);
        }

    }

    @Override
    public int getItemCount() {
        return pharmacies.size();
    }
}
