package ru.sibsutis.productm;

interface MainContract {
    interface View {
        void toStartActivity();
        void toBasicActivity();
        void showErrorServer();
    }

    interface Presenter {
        void startProgram();
    }
}
