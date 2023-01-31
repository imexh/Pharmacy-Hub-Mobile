package noobz69.sltc.pharmacyhubmobileclient.classes;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import noobz69.sltc.pharmacyhubmobileclient.customerview.Cart;
import noobz69.sltc.pharmacyhubmobileclient.customerview.EditProfile;
import noobz69.sltc.pharmacyhubmobileclient.customerview.Homepage;
import noobz69.sltc.pharmacyhubmobileclient.customerview.NottificationsPage;
import noobz69.sltc.pharmacyhubmobileclient.customerview.OrdersPage;
import noobz69.sltc.pharmacyhubmobileclient.customerview.SearchPage;

public class ToolbarNavigation extends AppCompatActivity {

    public void ClickHome(View view)
    {
        Homepage.activity.finish();
        redirectActivity(this, Homepage.class);
//        Toast.makeText(getApplicationContext(), "Under Construction", Toast.LENGTH_SHORT).show();

    }
    public void ClickSearch(View view)
    {
        redirectActivity(this, SearchPage.class);
//        Toast.makeText(getApplicationContext(), "Under Construction", Toast.LENGTH_SHORT).show();

    }
    public void ClickOrders(View view)
    {
        redirectActivity(this, OrdersPage.class);
//        Toast.makeText(getApplicationContext(), "Under Construction", Toast.LENGTH_SHORT).show();

    }
    public void ClickNotification(View view)
    {
        redirectActivity(this, NottificationsPage.class);
//        Toast.makeText(getApplicationContext(), "Under Construction", Toast.LENGTH_SHORT).show();

    }

    public void ClickCart(View view)
    {
        redirectActivity(this, Cart.class);
//        Toast.makeText(getApplicationContext(), "Under Construction", Toast.LENGTH_SHORT).show();

    }

    public void ClickProfile(View view)
    {
        redirectActivity(this, EditProfile.class);
//        Toast.makeText(getApplicationContext(), "Under Construction", Toast.LENGTH_SHORT).show();

    }

    public static void redirectActivity(Activity activity, Class aclass){
        Intent intent = new Intent(activity, aclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
