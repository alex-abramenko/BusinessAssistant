package ru.sibsutis.productm.ui.Basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;

import ru.sibsutis.productm.Model.JDBC;
import ru.sibsutis.productm.R;
import ru.sibsutis.productm.ServerHelper.MiniProductAccounting;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Product;

public class BasicActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_basic);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_adding, R.id.navigation_products, R.id.navigation_reports).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        context = this;

        cacheProduct();
    }

    private void cacheProduct() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MiniProductAccounting server = new MiniProductAccounting();
                try {
                    ArrayList<Product> products = server.getAllProducts();
                    if (products.size() > 0) {
                        JDBC jdbc = new JDBC(context);
                        jdbc.deleteAllProduct();
                        for (int i = 0; i < products.size(); i++)
                            jdbc.addProduct(products.get(i));
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        JDBC jdbc = new JDBC(this);
        jdbc.deleteAllBasket();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        /** DO NOTHING **/
    }
}
