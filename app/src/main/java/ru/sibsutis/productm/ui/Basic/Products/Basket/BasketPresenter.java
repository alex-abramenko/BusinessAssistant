package ru.sibsutis.productm.ui.Basic.Products.Basket;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import ru.sibsutis.productm.Model.JDBC;
import ru.sibsutis.productm.ServerHelper.MiniProductAccounting;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Product;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Trade;

class BasketPresenter implements BasketContract.Presenter {
    BasketContract.View view;

    public BasketPresenter(BasketContract.View v) {
        this.view = v;
    }

    @Override
    public void getAllBasket() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JDBC jdbc = new JDBC((Context) view);
                ArrayList<Basket> baskets = jdbc.getAllBasket();

                if (baskets.size() > 0) {
                    float itog = 0f;
                    for (int i = 0; i < baskets.size(); i++) {
                        Product pr = jdbc.getProduct(baskets.get(i).ID_Product);
                        itog += pr.Price * (float) baskets.get(i).Quantity;
                    }
                    view.showAllBasket(baskets, itog);
                } else
                    view.showNonBasket();
            }
        }).start();
    }

    @Override
    public void deleteAllBasket() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JDBC jdbc = new JDBC((Context) view);
                jdbc.deleteAllBasket();
                view.showNonBasket();
            }
        }).start();
    }

    @Override
    public void addTrade() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MiniProductAccounting server = new MiniProductAccounting();
                JDBC jdbc = new JDBC((Context) view);

                ArrayList<Basket> baskets = jdbc.getAllBasket();

                long date = new Date().getTime();
                try {
                    for (int i = 0; i < baskets.size(); i++) {
                        Product pr = jdbc.getProduct(baskets.get(i).ID_Product);
                        float price = pr.Price * (float) baskets.get(i).Quantity;
                        server.addTrade(baskets.get(i).ID_Product, baskets.get(i).Quantity, price, date);
                    }

                    jdbc.deleteAllBasket();
                    view.showNonBasket();
                    view.showGoodAddTrade();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
