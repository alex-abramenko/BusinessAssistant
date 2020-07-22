package ru.sibsutis.productm.ui.Basic.Products;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import ru.sibsutis.productm.R;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Product;
import ru.sibsutis.productm.ui.Basic.Products.Basket.BasketActivity;


public class ProductsFragment extends Fragment implements ProductsContract.View {
    private ProductsContract.Presenter presenter;

    private ProductsContract.View view;
    private Context mainContext;
    private ListView listV_products;
    private Button btn_basket;
    private ProgressBar pBar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_products, container, false);

        presenter = new ProductsPresenter(this, getActivity());
        view = this;
        mainContext = getActivity();

        return root;
    }

    private void toBasketActivity() {
        Intent intent = new Intent(getActivity(), BasketActivity.class);
        //intent.putExtra("user", "msg");
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().findViewById(R.id.navigation_products).setClickable(false);
        getActivity().findViewById(R.id.navigation_adding).setClickable(true);
        getActivity().findViewById(R.id.navigation_reports).setClickable(true);

        listV_products = getActivity().findViewById(R.id.listV_products_products);
        btn_basket = getActivity().findViewById(R.id.btn_products_basket);
        pBar = getActivity().findViewById(R.id.pBar_products);

        btn_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toBasketActivity();
            }
        });

        pBar.setVisibility(View.VISIBLE);
        listV_products.setVisibility(View.GONE);

        presenter.getAllProduct();
    }

    @Override
    public void showAllProduct(final ArrayList<Product> products) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pBar.setVisibility(View.GONE);
                listV_products.setVisibility(View.VISIBLE);

                ProductsListAdapter adapter = new ProductsListAdapter(
                        mainContext,
                        R.layout.item_product,
                        products,
                        view
                );
                listV_products.setAdapter(adapter);
            }
        });
    }


    @Override
    public void showNonProduct() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pBar.setVisibility(View.GONE);
                listV_products.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void addInBasket(int ID_Product, String Tittle_Product, int Quantity) {
        presenter.addInBasket(ID_Product, Tittle_Product, Quantity);
    }

    @Override
    public void showErrorAddInBasket() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(mainContext, "Недостаточно товара!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    //    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        Log.d(LOG_TAG, "ADD onAttach");
//    }
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d(LOG_TAG, "ADD onCreate");
//    }
//
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Log.d(LOG_TAG, "ADD onActivityCreated");
//    }
//
//    public void onStart() {
//        super.onStart();
//        Log.d(LOG_TAG, "ADD onStart");
//    }
//
//    public void onResume() {
//        super.onResume();
//        Log.d(LOG_TAG, "ADD onResume");
//    }
//
//    public void onPause() {
//        super.onPause();
//        Log.d(LOG_TAG, "ADD onPause");
//    }
//
//    public void onStop() {
//        super.onStop();
//        Log.d(LOG_TAG, "ADD onStop");
//    }
//
//    public void onDestroyView() {
//        super.onDestroyView();
//        Log.d(LOG_TAG, "ADD onDestroyView");
//    }
//
//    public void onDestroy() {
//        super.onDestroy();
//        Log.d(LOG_TAG, "ADD onDestroy");
//    }
//
//    public void onDetach() {
//        super.onDetach();
//        Log.d(LOG_TAG, "ADD onDetach");
//    }
}
