package ru.sibsutis.productm.ui.Basic.Adding;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.sibsutis.productm.R;
import ru.sibsutis.productm.ui.Basic.Adding.AddingCost.AddingCostActivity;
import ru.sibsutis.productm.ui.Basic.Adding.AddingProduct.AddingProductActivity;
import ru.sibsutis.productm.ui.Basic.Adding.AddingPurchpr.AddingPurchprActivity;

public class AddingFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_adding, container, false);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        Button btn_toAddingProduct = getActivity().findViewById(R.id.btn_adding_product);
        Button btn_toAddingPurchpr = getActivity().findViewById(R.id.btn_adding_purchpr);
        Button btn_toAddingCost = getActivity().findViewById(R.id.btn_adding_cost);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case (R.id.btn_adding_product):
                        toAddingProductActivity();
                        break;

                    case (R.id.btn_adding_purchpr):
                        toAddingPurchprActivity();
                        break;

                    case (R.id.btn_adding_cost):
                        toAddingCostActivity();
                        break;
                }
            }
        };

        btn_toAddingProduct.setOnClickListener(click);
        btn_toAddingPurchpr.setOnClickListener(click);
        btn_toAddingCost.setOnClickListener(click);

        getActivity().findViewById(R.id.navigation_products).setClickable(true);
        getActivity().findViewById(R.id.navigation_adding).setClickable(false);
        getActivity().findViewById(R.id.navigation_reports).setClickable(true);
    }

    private void toAddingProductActivity() {
        Intent intent = new Intent(getActivity(), AddingProductActivity.class);
        //intent.putExtra("user", "msg");
        startActivity(intent);
    }

    private void toAddingPurchprActivity() {
        Intent intent = new Intent(getActivity(), AddingPurchprActivity.class);
        //intent.putExtra("user", "msg");
        startActivity(intent);
    }

    private void toAddingCostActivity() {
        Intent intent = new Intent(getActivity(), AddingCostActivity.class);
        //intent.putExtra("user", "msg");
        startActivity(intent);
    }
}
