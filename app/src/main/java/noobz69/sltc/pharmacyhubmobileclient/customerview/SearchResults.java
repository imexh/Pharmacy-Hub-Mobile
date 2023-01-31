package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class SearchResults extends ToolbarNavigation {
    String searchText;
    ImageView image;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        searchText = SearchPage.searchText;
        image = findViewById(R.id.imageMedicineLayout);
        name = findViewById(R.id.pharmacyNameLayout);

        String pharmacyUrl = "https://static.vecteezy.com/system/resources/previews/007/438/687/original/illustration-of-a-pharmacy-health-pharmacy-logo-free-vector.jpg";
        String medicineUrl = "https://www.healthguard.lk/pub/media/Home/HG-home-prescription-banner_1_.jpg";

        if (SearchPage.pharmacyDetails == null)
        {
            if(!SearchPage.medicineDetails.containsKey("imageUrl"))
            {
                Glide.with(SearchResults.this).load(medicineUrl).into(image);
            }
            else
            {
                Glide.with(SearchResults.this).load(SearchPage.medicineDetails.get("imageUrl")).into(image);
            }
            name.setText(SearchPage.medicineDetails.get("medicineName"));
        }
        else
        {
            if(!SearchPage.medicineDetails.containsKey("imageUrl"))
            {
                Glide.with(SearchResults.this).load(pharmacyUrl).into(image);
            }
            else
            {
                Glide.with(SearchResults.this).load(SearchPage.medicineDetails.get("imageUrl")).into(image);
            }
            name.setText(SearchPage.pharmacyDetails.get("pharmacyName"));
        }


    }
}