package ru.sibsutis.productm.ui.Basic.Adding.AddingCost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import ru.sibsutis.productm.R;

public class AddingCostActivity extends AppCompatActivity implements AddingCostContract.View {
    private AddingCostContract.Presenter presenter;

    private EditText eTxt_tittle;
    private EditText eTxt_price;
    private Button btn_ok;
    private ImageButton btn_back;
    private ProgressBar pBar;
    private Context mainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_adding_cost);

        presenter = new AddingCostPresenter(this);

        mainContext = this;
        eTxt_tittle = findViewById(R.id.eTxt_addCost_tittle);
        eTxt_price = findViewById(R.id.eTxt_addCost_price);
        btn_ok = findViewById(R.id.btn_addCost_ok);
        btn_back = findViewById(R.id.iBtn_addCost_back);
        pBar = findViewById(R.id.pBar_addCost);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTxt_tittle.setEnabled(false);
                eTxt_price.setEnabled(false);
                btn_ok.setVisibility(View.GONE);
                pBar.setVisibility(View.VISIBLE);

                presenter.addCost(
                        eTxt_tittle.getText().toString(),
                        Float.valueOf(eTxt_price.getText().toString())
                );
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void showGoodAddCost() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                eTxt_tittle.setEnabled(true);
                eTxt_price.setEnabled(true);
                btn_ok.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);

                eTxt_tittle.setText("");
                eTxt_price.setText("");

                Toast toast = Toast.makeText(mainContext, "Расход успешно добавлен!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    @Override
    public void showErrorAddCost() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                eTxt_tittle.setEnabled(true);
                eTxt_price.setEnabled(true);
                btn_ok.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);

                Toast toast = Toast.makeText(mainContext, "Произошла ошибка при добавление!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}
