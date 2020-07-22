package ru.sibsutis.productm.ui.Basic.Products.Basket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.sibsutis.productm.R;

public class BasketActivity extends AppCompatActivity implements BasketContract.View {
    private BasketContract.Presenter presenter;
    private Context context;

    private ImageButton iBtn_back;
    private Button btn_clear;
    private Button btn_ok;
    private ListView listV_basket;
    private TextView tV_itog;
    private ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_basket);

        presenter = new BasketPresenter(this);
        context = this;

        iBtn_back = findViewById(R.id.iBtn_basket_back);
        btn_clear = findViewById(R.id.btn_basket_clear);
        btn_ok = findViewById(R.id.btn_basket_ok);
        listV_basket = findViewById(R.id.listV_basket_basket);
        tV_itog = findViewById(R.id.tV_basket_itog);
        pBar = findViewById(R.id.pBar_basket);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case(R.id.iBtn_basket_back):
                        finish();
                        break;

                    case(R.id.btn_basket_clear):
                        presenter.deleteAllBasket();
                        break;

                    case(R.id.btn_basket_ok):
                        btn_clear.setEnabled(false);
                        listV_basket.setVisibility(View.INVISIBLE);
                        tV_itog.setVisibility(View.INVISIBLE);
                        btn_ok.setVisibility(View.INVISIBLE);
                        pBar.setVisibility(View.VISIBLE);

                        presenter.addTrade();
                        break;
                }
            }
        };

        iBtn_back.setOnClickListener(click);
        btn_clear.setOnClickListener(click);
        btn_ok.setOnClickListener(click);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_clear.setEnabled(false);
        listV_basket.setVisibility(View.GONE);
        tV_itog.setVisibility(View.GONE);
        btn_ok.setVisibility(View.GONE);
        pBar.setVisibility(View.VISIBLE);

        presenter.getAllBasket();
    }

    @Override
    public void showAllBasket(final ArrayList<Basket> baskets, final float itog) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btn_clear.setEnabled(true);
                listV_basket.setVisibility(View.VISIBLE);
                tV_itog.setVisibility(View.VISIBLE);
                btn_ok.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);

                BasketsListAdapter adapter = new BasketsListAdapter(
                        context,
                        R.layout.item_basket,
                        baskets,
                        (BasketContract.View) context
                );
                listV_basket.setAdapter(adapter);
                tV_itog.setText(String.format("%.1f руб.", itog));
            }
        });
    }

    @Override
    public void showNonBasket() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btn_clear.setEnabled(false);
                listV_basket.setVisibility(View.GONE);
                tV_itog.setVisibility(View.GONE);
                btn_ok.setVisibility(View.GONE);
                pBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showGoodAddTrade() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(context, "Покупка успешно добавлена!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}
