package ru.sibsutis.productm.ui.Basic.Products;

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


class ProductsListAdapter extends ArrayAdapter<Product> {
    final String LOG_TAG = "myLogs";

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Product> products;
    private ProductsContract.View mainView;

    public ProductsListAdapter(Context context, int resource, ArrayList<Product> vacancies, ProductsContract.View view) {
        super(context, resource, vacancies);

        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.products = vacancies;
        this.mainView = view;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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

        viewHolder.tV_tittle.setText(String.format("%s). %s", (position+1), product.Tittle));
        viewHolder.tV_detail.setText(product.Detail);
        viewHolder.tV_price.setText(String.valueOf(product.Price) + " руб.");
        viewHolder.tV_quantity.setText(String.valueOf(product.Quantity) + " шт.");

        viewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 19.02.2020 сделать обработку Edit.
            }
        });

        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 19.02.2020 сделать обработку Delete.
            }
        });
        
        viewHolder.btn_inBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!viewHolder.eT_numPr.getText().toString().equals("")) {
                    if(product.Quantity >= Integer.parseInt(viewHolder.eT_numPr.getText().toString())) {
                        mainView.addInBasket(
                                product.ID_Product,
                                product.Tittle,
                                Integer.parseInt(viewHolder.eT_numPr.getText().toString()));
                        products.get(position).Quantity -= Integer.parseInt(viewHolder.eT_numPr.getText().toString());
                        viewHolder.tV_quantity.setText(String.valueOf(products.get(position).Quantity) + " шт.");
                        viewHolder.eT_numPr.setText("");
                    } else
                        mainView.showErrorAddInBasket();
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {
        Button btn_edit, btn_delete, btn_inBasket;
        TextView tV_tittle, tV_detail, tV_price, tV_quantity;
        EditText eT_numPr;

        ViewHolder(View view){
            btn_edit = view.findViewById(R.id.btn_prItem_edit);
            btn_delete = view.findViewById(R.id.btn_prItem_delete);
            tV_tittle = view.findViewById(R.id.tV_prItem_tittle);
            tV_detail = view.findViewById(R.id.tV_prItem_detail);
            tV_price = view.findViewById(R.id.tV_prItem_price);
            tV_quantity = view.findViewById(R.id.tV_prItem_quantity);
            eT_numPr = view.findViewById(R.id.eT_prItem_numPr);
            btn_inBasket = view.findViewById(R.id.btn_prItem_inBasket);
        }
    }
}