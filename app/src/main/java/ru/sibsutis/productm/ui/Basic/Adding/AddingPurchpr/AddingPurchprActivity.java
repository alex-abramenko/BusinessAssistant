package ru.sibsutis.productm.ui.Basic.Adding.AddingPurchpr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import ru.sibsutis.productm.R;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Product;

public class AddingPurchprActivity extends AppCompatActivity implements AddingPurchprContract.View {
    private AddingPurchprContract.Presenter presenter;

    private ListView listView_purchpr;
    private ImageButton btn_back;
    private Context mainContext;
    private ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_adding_purchpr);

        presenter = new AddingPurchprPresenter(this);

        listView_purchpr = findViewById(R.id.listV_addPurchpr_purchpr);
        btn_back = findViewById(R.id.iBtn_addPurchpr_back);
        pBar = findViewById(R.id.pBar_addPurchpr);
        mainContext = this;

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        listView_purchpr.setVisibility(View.GONE);
        pBar.setVisibility(View.VISIBLE);
        presenter.getAllProduct();
    }

    @Override
    public void showAllProduct(final ArrayList<Product> products) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView_purchpr.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);

                PurchprListAdapter adapter = new PurchprListAdapter(
                        mainContext,
                        R.layout.item_add_purchpr,
                        products,
                        (AddingPurchprContract.View) mainContext
                );
                listView_purchpr.setAdapter(adapter);
            }
        });
    }

    @Override
    public void addPurchpr(int ID_Product, int Quantity, float Price) {
        listView_purchpr.setVisibility(View.GONE);
        pBar.setVisibility(View.VISIBLE);
        presenter.addPurchpr(ID_Product, Quantity, Price);
    }

    @Override
    public void showGoodAddPurchpr() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView_purchpr.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);

                Toast toast = Toast.makeText(mainContext, "Закупка товара успешно добавлена!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}
