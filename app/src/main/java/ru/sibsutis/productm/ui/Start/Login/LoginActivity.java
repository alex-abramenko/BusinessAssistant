package ru.sibsutis.productm.ui.Start.Login;

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
import ru.sibsutis.productm.ui.Basic.BasicActivity;


public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private LoginContract.Presenter presenter;

    private EditText eTxt_login;
    private EditText eTxt_pass;
    private Button btn_OK;
    private ImageButton btn_back;
    private ProgressBar pBar;

    private Context mainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        eTxt_login = findViewById(R.id.eTxt_login_login);
        eTxt_pass = findViewById(R.id.eTxt_login_pass);
        btn_OK = findViewById(R.id.btn_login_ok);
        pBar = findViewById(R.id.pBar_login);
        btn_back = findViewById(R.id.iBtn_login_back);
        mainContext = this;

        presenter = new LoginPresenter(this);

        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTxt_login.setEnabled(false);
                eTxt_pass.setEnabled(false);
                btn_OK.setVisibility(View.GONE);
                pBar.setVisibility(View.VISIBLE);

                presenter.login(eTxt_login.getText().toString(), eTxt_pass.getText().toString());
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
    public void toStartActivity() {
        finish();
    }

    @Override
    public void toBasicActivity() {
        Intent intent = new Intent(this, BasicActivity.class);
        //intent.putExtra("user", "msg");
        startActivity(intent);
        finish();
    }

    @Override
    public void printErrorLogin() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                eTxt_login.setEnabled(true);
                eTxt_pass.setEnabled(true);
                btn_OK.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);

                Toast toast = Toast.makeText(mainContext, "Неверый логин или пароль!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}
