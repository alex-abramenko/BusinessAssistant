package ru.sibsutis.productm;

import android.content.Context;

import java.io.IOException;

import ru.sibsutis.productm.Model.JDBC;
import ru.sibsutis.productm.ServerHelper.MiniProductAccounting;


class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    MainPresenter(MainContract.View v) {
        view = v;
    }

    @Override
    public void startProgram() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MiniProductAccounting server = new MiniProductAccounting();
                if(!server.checkServerStatus())
                    view.showErrorServer();
                else {
                    JDBC jdbc = new JDBC((Context) view);
                    String[] str = jdbc.getAuthData();

                    if(str != null) {
                        try {
                            if(server.authorization(str[0], str[1])) {
                                jdbc.addCurrentUser(server.getUserInfo(str[0]));
                                view.toBasicActivity();
                            } else
                                view.toStartActivity();
                        }
                        catch (IOException e) { e.printStackTrace(); }
                    } else
                        view.toStartActivity();
                }
            }
        }).start();
    }
}
