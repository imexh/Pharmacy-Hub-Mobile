package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.classes.Reminder;

public class ReminderListAdapter extends RecyclerView.Adapter<ReminderItemViewHolder>{
    List<Reminder> reminders;

    public ReminderListAdapter(List<Reminder> reminders)
    {
        this.reminders = reminders;
    }

    @NonNull
    @Override
    public ReminderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_item_layout, parent, false);
        return new ReminderItemViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderItemViewHolder holder, int position) {
        String Title = reminders.get(position).getTitle();
        String Description = reminders.get(position).getDescription();
        String Date = reminders.get(position).getDate();
        String Time = reminders.get(position).getTime();

        holder.title.setText(Title);
        holder.description.setText(Description);
        holder.date.setText(Date);
        holder.time.setText(Time);
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }
}
