package ru.sibsutis.productm.ui.Basic.Adding.AddingProduct;

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

public class AddingProductActivity extends AppCompatActivity implements AddingProductContract.View {
    private AddingProductContract.Presenter presenter;

    private EditText eTxt_tittle;
    private EditText eTxt_detail;
    private EditText eTxt_price;
    private Button btn_ok;
    private ImageButton btn_back;
    private ProgressBar pBar;
    private Context mainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_adding_product);

        presenter = new AddingProductPresenter(this);

        mainContext = this;
        eTxt_tittle = findViewById(R.id.eTxt_addPr_tittle);
        eTxt_detail = findViewById(R.id.eTxt_addPr_detail);
        eTxt_price = findViewById(R.id.eTxt_addPr_price);
        btn_ok = findViewById(R.id.btn_addPr_ok);
        btn_back = findViewById(R.id.iBtn_addPr_back);
        pBar = findViewById(R.id.pBar_addPr);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTxt_tittle.setEnabled(false);
                eTxt_detail.setEnabled(false);
                eTxt_price.setEnabled(false);
                btn_ok.setVisibility(View.GONE);
                pBar.setVisibility(View.VISIBLE);

                presenter.addProduct(
                        eTxt_tittle.getText().toString(),
                        eTxt_detail.getText().toString(),
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
    public void showGoodAddPr() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                eTxt_tittle.setEnabled(true);
                eTxt_detail.setEnabled(true);
                eTxt_price.setEnabled(true);
                btn_ok.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);

                eTxt_tittle.setText("");
                eTxt_detail.setText("");
                eTxt_price.setText("");

                Toast toast = Toast.makeText(mainContext, "Товар успешно добавлен!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    @Override
    public void showErrorAddPr() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                eTxt_tittle.setEnabled(true);
                eTxt_detail.setEnabled(true);
                eTxt_price.setEnabled(true);
                btn_ok.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);

                Toast toast = Toast.makeText(mainContext, "Код товара уже занят!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}
