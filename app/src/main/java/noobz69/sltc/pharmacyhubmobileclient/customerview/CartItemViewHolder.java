package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import noobz69.sltc.pharmacyhubmobileclient.R;

public class CartItemViewHolder extends RecyclerView.ViewHolder{
    LinearLayout linearLayout;
    TextView medicineName, medicinePriceQty, removeBtn;

    public CartItemViewHolder(@NonNull View itemView) {
        super(itemView);
        linearLayout = itemView.findViewById(R.id.cartlayout);
        medicineName = itemView.findViewById(R.id.medicineNameCartLayout);
        medicinePriceQty = itemView.findViewById(R.id.qtyPriceCartLayout);
        removeBtn = itemView.findViewById(R.id.removeBtnCartLayout);
    }
}
