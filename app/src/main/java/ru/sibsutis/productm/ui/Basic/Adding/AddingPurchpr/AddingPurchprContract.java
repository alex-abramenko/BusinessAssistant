package ru.sibsutis.productm.ui.Basic.Adding.AddingPurchpr;

import java.util.ArrayList;

import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Product;

interface AddingPurchprContract {
    interface View {
        void showAllProduct(ArrayList<Product> products);
        void addPurchpr(int ID_Product, int Quantity, float Price);
        void showGoodAddPurchpr();
    }

    interface Presenter {
        void getAllProduct();
        void addPurchpr(int ID_Product, int Quantity, float Price);
    }
}
