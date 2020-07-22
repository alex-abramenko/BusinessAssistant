package ru.sibsutis.productm.ui.Start.Login;

interface LoginContract {
    interface View {
        void toStartActivity();
        void printErrorLogin();
        void toBasicActivity();
    }

    interface Presenter {
        void login(String login, String pass);
    }
}
