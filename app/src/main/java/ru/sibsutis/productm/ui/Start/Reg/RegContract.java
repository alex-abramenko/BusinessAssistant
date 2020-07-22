package ru.sibsutis.productm.ui.Start.Reg;

interface RegContract {
    interface View {
        void printErrorReg();
        void toStartActivity();
        void toLoginActivity();
    }

    interface Presenter {
        void reg(String login, String pass, String phone, String name, String shpTittle);
    }
}
