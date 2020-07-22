package ru.sibsutis.productm.ui.Start.Reg;

import java.io.IOException;

import ru.sibsutis.productm.ServerHelper.MiniProductAccounting;


class RegPresenter implements RegContract.Presenter {
    private RegContract.View view;

    public RegPresenter(RegContract.View view) {
        this.view = view;
    }

    public void reg(final String login, final String pass, final String phone, final String name, final String shpTittle) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MiniProductAccounting server = new MiniProductAccounting();
                try {
                    if(server.registration(login, pass, phone, name, null)) {
                        server.addShop(shpTittle, null, null, null, null);
                        view.toLoginActivity();
                    } else
                        view.printErrorReg();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    view.printErrorReg();
                }
            }
        }).start();
    }
}
