package ru.sibsutis.productm.ui.Basic.Adding.AddingCost;

interface AddingCostContract {
    interface View {
        void showGoodAddCost();
        void showErrorAddCost();
    }

    interface Presenter {
        void addCost(String Tittle, float Price);
    }
}
