package ru.sibsutis.productm.ui.Basic.Products;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

import ru.sibsutis.productm.Model.JDBC;
import ru.sibsutis.productm.ServerHelper.MiniProductAccounting;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Product;

class ProductsPresenter implements ProductsContract.Presenter {
    private ProductsContract.View view;
    private Context context;

    ProductsPresenter(ProductsContract.View v, Context c) {
        this.view = v;
        this.context = c;
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
                        JDBC jdbc = new JDBC(context);
                        jdbc.deleteAllProduct();
                        for (int i = 0; i < products.size(); i++)
                            jdbc.addProduct(products.get(i));
                        view.showAllProduct(products);
                    } else
                        view.showNonProduct();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void addInBasket(final int ID_Product, final String Tittle_Product, final int Quantity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JDBC jdbc = new JDBC(context);
                jdbc.addInBasket(ID_Product, Tittle_Product, Quantity);
            }
        }).start();
    }
}
