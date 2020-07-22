package ru.sibsutis.productm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.sibsutis.productm.ui.Basic.BasicActivity;
import ru.sibsutis.productm.ui.Start.StartActivity;


public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;

    private ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        pBar = findViewById(R.id.pBar_main);
        pBar.setVisibility(View.VISIBLE);

        presenter = new MainPresenter(this);
        presenter.startProgram();
    }

    @Override
    public void toStartActivity() {
        Intent intent = new Intent(this, StartActivity.class);
        intent.putExtra("user", "msg");
        startActivity(intent);
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
    public void showErrorServer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pBar.setVisibility(View.GONE);
                TextView tV_error = findViewById(R.id.tV_m_error);
                tV_error.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        /**
         * DO NOTHING
         */
    }
}
