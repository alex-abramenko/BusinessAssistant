package ru.sibsutis.productm.ui.Basic.Adding.AddingPurchpr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ru.sibsutis.productm.R;
import ru.sibsutis.productm.ServerHelper.Wrapper.Essence.Product;


class PurchprListAdapter extends ArrayAdapter<Product> {
    final String LOG_TAG = "myLogs";

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Product> products;
    private AddingPurchprContract.View mainView;

    public PurchprListAdapter(Context context, int resource, ArrayList<Product> vacancies, AddingPurchprContract.View view) {
        super(context, resource, vacancies);

        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.products = vacancies;
        this.mainView = view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Product product = products.get(position);

        viewHolder.tV_tittle.setText(product.Tittle);
        viewHolder.tV_detail.setText(product.Detail);

        viewHolder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!viewHolder.eT_price.getText().toString().equals("") &&
                    !viewHolder.eT_quantity.getText().toString().equals("")) {

                    mainView.addPurchpr(
                            product.ID_Product,
                            Integer.parseInt(viewHolder.eT_quantity.getText().toString()),
                            Float.parseFloat(viewHolder.eT_price.getText().toString())
                            );

                    viewHolder.eT_price.setText("");
                    viewHolder.eT_quantity.setText("");
                }

            }
        });

        return convertView;
    }

    private class ViewHolder {
        Button btn_add;
        TextView tV_tittle, tV_detail;
        EditText eT_price, eT_quantity;

        ViewHolder(View view){
            btn_add = view.findViewById(R.id.btn_aPurchprItem_add);
            tV_tittle = view.findViewById(R.id.tV_aPurchprItem_tittle);
            tV_detail = view.findViewById(R.id.tV_aPurchprItem_detail);
            eT_price = view.findViewById(R.id.eT_aPurchprItem_price);
            eT_quantity = view.findViewById(R.id.eT_aPurchprItem_quantity);
        }
    }
}