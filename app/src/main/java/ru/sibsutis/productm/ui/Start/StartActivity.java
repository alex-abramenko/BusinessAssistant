package ru.sibsutis.productm.ui.Start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ru.sibsutis.productm.R;
import ru.sibsutis.productm.ui.Start.Login.LoginActivity;
import ru.sibsutis.productm.ui.Start.Reg.RegActivity;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_start);

        Button btn_reg = findViewById(R.id.btn_start_reg);
        Button btn_log = findViewById(R.id.btn_start_login);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case(R.id.btn_start_reg):
                        toRegActivity();
                        break;

                    case(R.id.btn_start_login):
                        toLoginActivity();
                        break;
                }
            }
        };

        btn_reg.setOnClickListener(click);
        btn_log.setOnClickListener(click);
    }

    private void toRegActivity() {
        Intent intent = new Intent(this, RegActivity.class);
        //intent.putExtra("user", "msg");
        startActivity(intent);
    }

    private void toLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        //intent.putExtra("user", "msg");
        startActivity(intent);
    }


}
