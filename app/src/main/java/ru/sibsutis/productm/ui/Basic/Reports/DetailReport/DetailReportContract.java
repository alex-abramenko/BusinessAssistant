package ru.sibsutis.productm.ui.Basic.Reports.DetailReport;

import java.util.ArrayList;

import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Cost;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.PurchProducts;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.ReportItem;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Trade;

interface DetailReportContract {
    interface View {
        void showAllTrades(ArrayList<Trade> trades);
        void showAllCosts(ArrayList<Cost> costs);
        void showAllPurchPr(ArrayList<PurchProducts> purchProducts);
        void showBaseReport(ArrayList<ReportItem> reportItems, float costs, float profit);
    }

    interface Presenter {
        void getAllTrades(String startDate, String endDate);
        void getAllCosts(String startDate, String endDate);
        void getAllPurchPr(String startDate, String endDate);
        void getBaseReport(String startDate, String endDate);
    }
}
