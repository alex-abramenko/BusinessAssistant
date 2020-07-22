package ru.sibsutis.productm.ui.Basic.Products;

import java.util.ArrayList;

import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Product;

interface ProductsContract {
    interface View {
        void showAllProduct(ArrayList<Product> products);
        void showNonProduct();
        void addInBasket(int ID_Product, String Tittle_Product, int Quantity);
        void showErrorAddInBasket();
    }

    interface Presenter {
        void getAllProduct();
        void addInBasket(int ID_Product, String Tittle_Product, int Quantity);
    }
}
