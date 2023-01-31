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
import noobz69.sltc.pharmacyhubmobileclient.classes.Medicine;

public class AllMedicineListAdapter extends RecyclerView.Adapter<MedicineItemViewHolder>{
    List<Medicine> medicines;
    public static String medicineId;
    public static Medicine medicineDetail;

    public AllMedicineListAdapter(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    @NonNull
    @Override
    public MedicineItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_item_layout, parent, false);
        return new MedicineItemViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineItemViewHolder holder, int position) {
        String MedicineName = medicines.get(position).getMedicineName();
        String ImageUrl = medicines.get(position).getImageUrl();
        String url = "https://www.healthguard.lk/pub/media/Home/HG-home-prescription-banner_1_.jpg";

        holder.medicineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicineId = medicines.get(position).getMedicineId();
                medicineDetail = medicines.get(position);
                Intent intent = new Intent(view.getContext(), MedicineDescription2.class);
                view.getContext().startActivity(intent);
            }
        });

        holder.medicineName.setText(MedicineName);

        if (medicines.get(position).getImageUrl() == null)
        {
            Glide.with(AllMedicines.activity).load(url).into(holder.medicineImage);
        }
        else
        {
            Glide.with(AllMedicines.activity).load(ImageUrl).into(holder.medicineImage);
        }
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }
}
