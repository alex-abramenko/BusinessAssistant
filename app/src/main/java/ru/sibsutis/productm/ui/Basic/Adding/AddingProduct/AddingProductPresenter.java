package ru.sibsutis.productm.ui.Basic.Adding.AddingProduct;

import java.io.IOException;

import ru.sibsutis.productm.ServerHelper.MiniProductAccounting;

class AddingProductPresenter implements AddingProductContract.Presenter {
    private AddingProductContract.View view;

    AddingProductPresenter(AddingProductContract.View v) {
        this.view = v;
    }

    @Override
    public void addProduct(final String Tittle, final String Detail, final float Price) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MiniProductAccounting server = new MiniProductAccounting();
                try {
                    if(server.addProduct(Tittle, Detail, Price))
                        view.showGoodAddPr();
                    else
                        view.showErrorAddPr();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    view.showErrorAddPr();
                }
            }
        }).start();
    }
}
