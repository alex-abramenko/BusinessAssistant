package ru.sibsutis.productm.ui.Basic.Products.Basket;

import java.util.ArrayList;

interface BasketContract {
    interface View {
        void showAllBasket(ArrayList<Basket> baskets, float itog);
        void showNonBasket();
        void showGoodAddTrade();
    }

    interface Presenter {
        void getAllBasket();
        void deleteAllBasket();
        void addTrade();
    }
}
