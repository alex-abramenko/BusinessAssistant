package ru.sibsutis.productm.ui.Basic.Reports.DetailReport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import ru.sibsutis.productm.R;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Cost;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.PurchProducts;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.ReportItem;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Trade;
import ru.sibsutis.productm.ui.Basic.Reports.ReportsFragment;

public class DetailReportActivity extends AppCompatActivity implements DetailReportContract.View {
    private DetailReportContract.Presenter presenter;

    private ImageButton btn_back;
    private TextView tV_label;
    private ListView listV_report;
    private ProgressBar pBar;
    private LinearLayout layout_forBaseReport;
    private Context context;

    String startDate;
    String endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_detail_report);

        presenter = new DetailReportPresenter(this);

        Intent intent = getIntent();
        startDate = intent.getStringExtra("StartDate");
        endDate = intent.getStringExtra("EndDate");
        String choice = intent.getStringExtra("Choice");

        btn_back = findViewById(R.id.iBtn_detailReport_back);
        tV_label = findViewById(R.id.tV_detailReport_label);
        listV_report = findViewById(R.id.listV_detailReport_report);
        pBar = findViewById(R.id.pBar_detailReport);
        layout_forBaseReport = findViewById(R.id.layout_reports_forBaseReport);
        context = this;

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tV_label.setText(choice);

        listV_report.setVisibility(View.GONE);
        pBar.setVisibility(View.VISIBLE);
        layout_forBaseReport.setVisibility(View.GONE);

        if(choice.equals(ReportsFragment.CH_SPIN[0]))
            initTradesReport();

        if(choice.equals(ReportsFragment.CH_SPIN[1]))
            initCostsReport();

        if(choice.equals(ReportsFragment.CH_SPIN[2]))
            initPurchprReport();

        if(choice.equals(ReportsFragment.CH_SPIN[3]))
            initBaseReport();
    }

    private void initTradesReport() {
        presenter.getAllTrades(startDate, endDate);
    }

    private void initCostsReport() {
        presenter.getAllCosts(startDate, endDate);
    }

    private void initPurchprReport() {
        presenter.getAllPurchPr(startDate, endDate);
    }

    private void initBaseReport() {
        presenter.getBaseReport(startDate, endDate);
    }

    @Override
    public void showAllTrades(final ArrayList<Trade> trades) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pBar.setVisibility(View.GONE);
                listV_report.setVisibility(View.VISIBLE);

                TradesListAdapter adapter = new TradesListAdapter(
                        context,
                        R.layout.item_trade,
                        trades);
                listV_report.setAdapter(adapter);
            }
        });
    }

    @Override
    public void showAllCosts(final ArrayList<Cost> costs) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pBar.setVisibility(View.GONE);
                listV_report.setVisibility(View.VISIBLE);

                CostsListAdapter adapter = new CostsListAdapter(
                        context,
                        R.layout.item_cost,
                        costs);
                listV_report.setAdapter(adapter);
            }
        });
    }

    @Override
    public void showAllPurchPr(final ArrayList<PurchProducts> purchProducts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pBar.setVisibility(View.GONE);
                listV_report.setVisibility(View.VISIBLE);

                PurchprListAdapter adapter = new PurchprListAdapter(
                        context,
                        R.layout.item_purchpr,
                        purchProducts);
                listV_report.setAdapter(adapter);
            }
        });
    }

    @Override
    public void showBaseReport(final ArrayList<ReportItem> reportItems, final float costs, final float profit) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pBar.setVisibility(View.GONE);
                listV_report.setVisibility(View.VISIBLE);
                layout_forBaseReport.setVisibility(View.VISIBLE);

                BaseReportListAdapter adapter = new BaseReportListAdapter(
                        context,
                        R.layout.item_basereport,
                        reportItems);
                listV_report.setAdapter(adapter);

                TextView label_costs = findViewById(R.id.tV_reports_costs);
                TextView label_profit = findViewById(R.id.tV_reports_profit);
                TextView label = findViewById(R.id.tV_reports_labelForProfit);

                label_costs.setText(String.format("%.1f руб.", costs));


                if(profit < 0) {
                    label.setText("Убытки: ");
                    label_profit.setText(String.format("%.1f руб.", profit*(-1f)));
                } else {
                    label.setText("Прибыль: ");
                    label_profit.setText(String.format("%.1f руб.", profit));
                }
            }
        });
    }
}
