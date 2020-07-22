package ru.sibsutis.productm.ui.Basic.Adding.AddingProduct;

interface AddingProductContract {
    interface View {
        void showGoodAddPr();
        void showErrorAddPr();
    }

    interface Presenter {
        void addProduct(String Tittle, String Detail, float Price);
    }
}
