package ru.sibsutis.productm.ui.Basic.Products.Basket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ru.sibsutis.productm.R;


class BasketsListAdapter extends ArrayAdapter<Basket> {
    final String LOG_TAG = "myLogs";

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Basket> baskets;
    private BasketContract.View mainView;

    public BasketsListAdapter(Context context, int resource, ArrayList<Basket> b, BasketContract.View view) {
        super(context, resource, b);

        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.baskets = b;
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

        Basket basket = this.baskets.get(position);

        viewHolder.tV_tittle.setText(basket.Tittle_Product);
        viewHolder.tV_quantity.setText(String.valueOf(basket.Quantity) + " шт.");

        return convertView;
    }

    private class ViewHolder {
        TextView tV_tittle, tV_quantity;

        ViewHolder(View view){
            tV_tittle = view.findViewById(R.id.tV_basketItem_tittle);
            tV_quantity = view.findViewById(R.id.tV_basketItem_quantity);
        }
    }
}