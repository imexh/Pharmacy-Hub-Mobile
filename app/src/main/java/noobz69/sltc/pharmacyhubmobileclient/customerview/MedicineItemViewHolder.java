package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import noobz69.sltc.pharmacyhubmobileclient.R;

public class MedicineItemViewHolder extends RecyclerView.ViewHolder{
    LinearLayout linearLayout;
    ConstraintLayout medicineBtn;
    TextView medicineName;
    ImageView medicineImage;

    public MedicineItemViewHolder(@NonNull View itemView) {
        super(itemView);
        medicineImage = itemView.findViewById(R.id.imageMedicineLayout);
        medicineName = itemView.findViewById(R.id.medicineNameLayout);
        medicineBtn = itemView.findViewById(R.id.medicineLayoutBtn);
        linearLayout = itemView.findViewById(R.id.medicinelayout);
    }
}
