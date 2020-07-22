package ru.sibsutis.productm.ui.Basic.Adding.AddingCost;

import java.io.IOException;
import java.util.Date;

import ru.sibsutis.productm.ServerHelper.MiniProductAccounting;

class AddingCostPresenter implements AddingCostContract.Presenter {
    AddingCostContract.View view;

    public AddingCostPresenter(AddingCostContract.View v) {
        this.view = v;
    }

    @Override
    public void addCost(final String Tittle, final float Price) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MiniProductAccounting server = new MiniProductAccounting();
                try {
                    Date date = new Date();
                    if(server.addCost(Tittle, Price, date.getTime()))
                        view.showGoodAddCost();
                    else
                        view.showErrorAddCost();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    view.showErrorAddCost();
                }
            }
        }).start();
    }
}
