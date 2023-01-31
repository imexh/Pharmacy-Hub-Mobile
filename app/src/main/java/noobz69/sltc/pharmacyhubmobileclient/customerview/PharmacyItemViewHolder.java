package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import noobz69.sltc.pharmacyhubmobileclient.R;

public class PharmacyItemViewHolder  extends RecyclerView.ViewHolder{
    TextView pharmacyName;
    ImageView pharmacyImage;
    LinearLayout linearLayout;
    ConstraintLayout pharmacyBtn;

    public PharmacyItemViewHolder(@NonNull View itemView) {
        super(itemView);
        pharmacyImage = itemView.findViewById(R.id.imageMedicineLayout);
        pharmacyName = itemView.findViewById(R.id.pharmacyNameLayout);
        pharmacyBtn = itemView.findViewById(R.id.medicineLayoutBtn);
        linearLayout = itemView.findViewById(R.id.pharmacylayout);
    }
}
