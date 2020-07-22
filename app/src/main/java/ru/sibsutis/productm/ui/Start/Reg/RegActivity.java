package ru.sibsutis.productm.ui.Start.Reg;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ru.sibsutis.productm.R;
import ru.sibsutis.productm.ui.Start.Login.LoginActivity;

public class RegActivity extends AppCompatActivity implements RegContract.View {
    private RegContract.Presenter presenter;
    private Context mainContext;

    private EditText eTxt_login;
    private EditText eTxt_pass;
    private EditText eTxt_phone;
    private EditText eTxt_name;
    private EditText eTxt_shopTittle;
    private Button btn_OK;
    private ImageButton btn_back;
    private ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_reg);

        eTxt_login = findViewById(R.id.eTxt_reg_login);
        eTxt_pass = findViewById(R.id.eTxt_reg_pass);
        eTxt_phone = findViewById(R.id.eTxt_reg_phone);
        eTxt_name = findViewById(R.id.eTxt_reg_name);
        eTxt_shopTittle = findViewById(R.id.eTxt_reg_shopTittle);
        btn_OK = findViewById(R.id.btn_reg_ok);
        btn_back = findViewById(R.id.iBtn_reg_back);
        pBar = findViewById(R.id.pBar_reg);

        presenter = new RegPresenter(this);
        mainContext = this;

        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTxt_login.setEnabled(false);
                eTxt_pass.setEnabled(false);
                eTxt_phone.setEnabled(false);
                eTxt_name.setEnabled(false);
                eTxt_shopTittle.setEnabled(false);
                btn_OK.setVisibility(View.GONE);
                pBar.setVisibility(View.VISIBLE);

                presenter.reg(
                        eTxt_login.getText().toString(),
                        eTxt_pass.getText().toString(),
                        eTxt_phone.getText().toString(),
                        eTxt_name.getText().toString(),
                        eTxt_shopTittle.getText().toString()
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
    public void printErrorReg() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                eTxt_login.setEnabled(true);
                eTxt_pass.setEnabled(true);
                eTxt_phone.setEnabled(true);
                eTxt_name.setEnabled(true);
                eTxt_shopTittle.setEnabled(true);
                btn_OK.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);

                Toast toast = Toast.makeText(mainContext, "Введенный логин уже занят!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    @Override
    public void toStartActivity() {
        finish();
    }

    @Override
    public void toLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        //intent.putExtra("user", "msg");
        startActivity(intent);
        finish();
    }
}
