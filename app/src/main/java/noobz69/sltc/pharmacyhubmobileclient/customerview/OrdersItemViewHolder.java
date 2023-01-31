package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import noobz69.sltc.pharmacyhubmobileclient.R;

public class OrdersItemViewHolder extends RecyclerView.ViewHolder{
    LinearLayout linearLayout;
    TextView orderId, pharmacyName, item;
    public OrdersItemViewHolder(@NonNull View itemView) {
        super(itemView);
        linearLayout = itemView.findViewById(R.id.orderslayout);
        orderId = itemView.findViewById(R.id.orderIdOrdersLayout);
        pharmacyName = itemView.findViewById(R.id.pharmacyNameOrdersrLayout);
        item = itemView.findViewById(R.id.itemOrdersLayout);
    }
}
