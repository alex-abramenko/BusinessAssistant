package ru.sibsutis.productm.ui.Start.Login;

import android.content.Context;

import java.io.IOException;

import ru.sibsutis.productm.Model.JDBC;
import ru.sibsutis.productm.ServerHelper.MiniProductAccounting;


class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void login(final String login, final String pass) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MiniProductAccounting server = new MiniProductAccounting();
                try {
                    if(server.authorization(login, pass)) {
                        JDBC jdbc = new JDBC((Context) view);
                        jdbc.addCurrentUser(server.getUserInfo(login));
                        jdbc.addAuthData(login, pass);
                        view.toBasicActivity();
                    } else
                        view.printErrorLogin();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    view.printErrorLogin();
                }
            }
        }).start();
    }
}
