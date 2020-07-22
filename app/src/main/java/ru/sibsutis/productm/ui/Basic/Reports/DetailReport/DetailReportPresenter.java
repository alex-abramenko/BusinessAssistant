package ru.sibsutis.productm.ui.Basic.Reports.DetailReport;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

import ru.sibsutis.productm.Model.JDBC;
import ru.sibsutis.productm.ServerHelper.MiniProductAccounting;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Cost;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Product;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.PurchProducts;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Report;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.ReportItem;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Trade;

class DetailReportPresenter implements DetailReportContract.Presenter {
    private DetailReportContract.View view;

    DetailReportPresenter(DetailReportContract.View v) {
        this.view = v;
    }

    @Override
    public void getAllTrades(final String startDate, final String endDate) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long sDate = MiniProductAccounting.parseStrToCal(startDate).getTimeInMillis();
                long eDate = MiniProductAccounting.parseStrToCal(endDate).getTimeInMillis();

                MiniProductAccounting server = new MiniProductAccounting();
                try {
                    ArrayList<Trade> trades = server.getTrades(sDate, eDate);
                    JDBC jdbc = new JDBC((Context) view);
                    for (int i = 0; i < trades.size(); i++) {
                        Product pr = jdbc.getProduct(trades.get(i).ID_Product);
                        trades.get(i).Tittle_Product = pr.Tittle;
                        trades.get(i).Detail_Product = pr.Detail;
                    }
                    view.showAllTrades(trades);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void getAllCosts(final String startDate, final String endDate) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long sDate = MiniProductAccounting.parseStrToCal(startDate).getTimeInMillis();
                long eDate = MiniProductAccounting.parseStrToCal(endDate).getTimeInMillis();

                MiniProductAccounting server = new MiniProductAccounting();
                try {
                    ArrayList<Cost> costs = server.getCosts(sDate, eDate);
                    view.showAllCosts(costs);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    public void getAllPurchPr(final String startDate, final String endDate) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long sDate = MiniProductAccounting.parseStrToCal(startDate).getTimeInMillis();
                long eDate = MiniProductAccounting.parseStrToCal(endDate).getTimeInMillis();

                MiniProductAccounting server = new MiniProductAccounting();
                try {
                    ArrayList<PurchProducts> purchProducts = server.getPurchPr(sDate, eDate);
                    JDBC jdbc = new JDBC((Context) view);
                    for (int i = 0; i < purchProducts.size(); i++) {
                        Product product = jdbc.getProduct(purchProducts.get(i).ID_Product);
                        purchProducts.get(i).Tittle_Product = product.Tittle;
                        purchProducts.get(i).Detail_Product = product.Detail;
                    }
                    view.showAllPurchPr(purchProducts);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    public void getBaseReport(final String startDate, final String endDate) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long sDate = MiniProductAccounting.parseStrToCal(startDate).getTimeInMillis();
                long eDate = MiniProductAccounting.parseStrToCal(endDate).getTimeInMillis();

                MiniProductAccounting server = new MiniProductAccounting();
                try {
                    Report report = server.getReport(sDate, eDate);
                    ArrayList<ReportItem> reportItems = new ArrayList<>();

                    for (int i = 0; i < report.idProducts.length; i++) {
                        ReportItem rItem = new ReportItem();
                        rItem.idProduct = report.idProducts[i];
                        rItem.tittleProduct = report.tittleProducts[i];
                        rItem.detailProduct = report.detailProducts[i];
                        rItem.quanProduct = report.quanProducts[i];
                        rItem.priceProduct = report.priceProducts[i];

                        reportItems.add(rItem);
                    }
                    view.showBaseReport(reportItems, report.costs, report.profit);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
