package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import noobz69.sltc.pharmacyhubmobileclient.R;

public class ReminderItemViewHolder extends RecyclerView.ViewHolder{

    TextView title, description, date, time;
    LinearLayout linearLayout;

    public ReminderItemViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.orderIdOrdersLayout);
        description = itemView.findViewById(R.id.descriptionReminderLayout);
        date = itemView.findViewById(R.id.dateReminderLayout);
        time = itemView.findViewById(R.id.timeReminderLayout);
        linearLayout = itemView.findViewById(R.id.reminderlayout);
    }
}
