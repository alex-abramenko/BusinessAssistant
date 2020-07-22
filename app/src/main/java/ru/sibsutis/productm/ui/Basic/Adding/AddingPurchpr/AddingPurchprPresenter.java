package ru.sibsutis.productm.ui.Basic.Adding.AddingPurchpr;

import android.content.Context;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.Date;

import ru.sibsutis.productm.Model.JDBC;
import ru.sibsutis.productm.ServerHelper.MiniProductAccounting;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Product;

class AddingPurchprPresenter implements AddingPurchprContract.Presenter {
    private AddingPurchprContract.View view;

    AddingPurchprPresenter(AddingPurchprContract.View v) {
        this.view = v;
    }

    @Override
    public void getAllProduct() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MiniProductAccounting server = new MiniProductAccounting();
                try {
                    ArrayList<Product> products = server.getAllProducts();
                    if(products.size() > 0) {
                        JDBC jdbc = new JDBC((Context) view);
                        jdbc.deleteAllProduct();
                        for (int i = 0; i < products.size(); i++)
                            jdbc.addProduct(products.get(i));
                        view.showAllProduct(products);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void addPurchpr(final int ID_Product, final int Quantity, final float Price) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MiniProductAccounting server = new MiniProductAccounting();

                try {
                    if(server.addPurchP(ID_Product, Quantity, Price, new Date().getTime()))
                        view.showGoodAddPurchpr();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
